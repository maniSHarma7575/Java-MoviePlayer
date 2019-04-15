package movieplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;






public class Movieplayer
  extends Application
{
  public Movieplayer() {}
  
  public void start(final Stage stage)
    throws Exception
  {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("AwaisMedia");
    scene.setOnMouseClicked(new EventHandler() {
      public void handle(MouseEvent me) {
        if (me.getClickCount() == 2) {
          stage.setFullScreen(true);
        }
      }
    });
    stage.show();
  }
  


  public static void main(String[] args)
  {
    launch(args);
  }
}
