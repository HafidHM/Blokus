package Vue;

import java.util.HashMap;

import Modele.Jeu;
import Patterns.Observateur;
import blokus.Framework;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App implements Observateur{

	Jeu jeu;
	private final Stage stage;
	private final Scene scene;
	private final Pane root;
	
	private final HashMap<String,View> viewMap;
	private final ObjectProperty<View> currentView;
	OnLaunch onLaunch;
	OnFinish onFinish;
	OnExit onExit;
	
	public App(Jeu j, Stage stage) {
		this.jeu = j;
		this.stage = stage;
		root = new StackPane();
		scene = new Scene(root);
		stage.setScene(scene);
		
		viewMap = new HashMap<>();
		currentView = new SimpleObjectProperty<View>();
		initFramework();
		initApp();
		jeu.ajouteObservateur(this);

	}
	private final void initFramework() {
		Framework.app = this;
	}
	
	private final void initApp() {
		stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (event)->{
			if(onExit != null && !onExit.handle()) {
				event.consume();
			}
		});
		
		currentView.addListener((o,ov,nv)->{
			if(ov != null) {
				ov.onLeave();
				root.getChildren().remove(ov.getPane());
			}
			if(nv != null) {
				root.getChildren().add(nv.getPane());
				nv.onEnter();
			}
		});
		
		
	}
	
	
	public Stage getStage() {
		return stage;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public String getTitle() {
		return stage.getTitle();
	}
	
	public void setTitle(String title) {
		stage.setTitle(title);
	}
	public StringProperty titleProperty() {
		return stage.titleProperty();
	}
	
	public double getWidth() {
		return root.getMinWidth();
	}
	
	public void setWidth(double width) {
		root.setMinWidth(width);
	}
	
	public DoubleProperty widthProperty() {
		return root.minWidthProperty();
	}
	
	public double getHeight() {
		return root.getMinHeight();
	}
	
	public void setHeight(double height) {
		root.setMinHeight(height);
	}
	
	public DoubleProperty HeightProperty() {
		return root.minHeightProperty();
	}
	
	public View getView(String name) {
		return viewMap.get(name); 
	}
	
	public void regView(String name, View view) {
		viewMap.put(name, view);
	}
	
	public void unregView(String name) {
		viewMap.remove(name);
	}
	
	public View getCurrentView() {
		return currentView.get();
	}
	
	public ReadOnlyObjectProperty<View> currentViewProperty(){
		return currentView;
	}
	
	public void gotoView(String name) {
		View view = viewMap.get(name);
		if(view != null) {
			currentView.set(view);
		}
	}
	void launch() {
		if(onLaunch != null) {
			onLaunch.handle();
		}
		for(View view: viewMap.values()) {
			view.onLaunch();
		}
		stage.requestFocus();
		stage.show();
	}
	
	public void finish() {
		for(View view: viewMap.values()) {
			view.onFinish();
		}
		if(onFinish != null) {
			onFinish.handle();
		}
	}
	
	public void exit() {
		Platform.exit();
	}
	
	public static interface OnLaunch{
		void handle();
	}
	
	public static interface OnFinish{
		void handle();
	}
	
	public static interface OnExit{
		boolean handle();
	}

	@Override
	public void miseAJour() {
		
		
	}

	
}
