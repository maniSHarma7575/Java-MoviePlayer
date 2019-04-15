package movieplayer;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;









public class FXMLDocumentController
  implements Initializable
{
  @FXML
  private MediaView mediaView;
  private MediaPlayer mediaPlayer;
  private String filePath;
  @FXML
  private Slider slider;
  @FXML
  private HBox menuBar;
  @FXML
  private Slider seekSlider;
  
  public FXMLDocumentController() {}
  
  @FXML
  private void handleButtonAction(ActionEvent event)
  {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.mp4)", new String[] { "*.mp4" });
    fileChooser.getExtensionFilters().add(filter);
    File file = fileChooser.showOpenDialog(null);
    filePath = file.toURI().toString();
    if (filePath != null) {
      Media media = new Media(filePath);
      mediaPlayer = new MediaPlayer(media);
      mediaView.setMediaPlayer(mediaPlayer);
      DoubleProperty width = mediaView.fitWidthProperty();
      DoubleProperty hieght = mediaView.fitHeightProperty();
      width.bind(Bindings.selectDouble(mediaView.sceneProperty(), new String[] { "width" }));
      hieght.bind(Bindings.select(mediaView.sceneProperty(), new String[] { "hieght" }));
      slider.setValue(mediaPlayer.getVolume() * 100.0D);
      slider.valueProperty().addListener(new InvalidationListener()
      {
        public void invalidated(Observable observable) {
          mediaPlayer.setVolume(slider.getValue() / 100.0D);
        }
        

      });
      mediaPlayer.currentTimeProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
          seekSlider.setValue(newValue.toSeconds());
        }
        
      });
      seekSlider.setOnMouseClicked(new EventHandler()
      {
        public void handle(MouseEvent event) {
          mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
        }
      });
      mediaPlayer.play();
    }
  }
  
  @FXML
  private void pauseVideo(ActionEvent event)
  {
    mediaPlayer.pause();
  }
  
  @FXML
  private void exitVideo(ActionEvent event) { System.exit(0); }
  
  @FXML
  private void stopVideo(ActionEvent event) {
    mediaPlayer.stop();
  }
  
  @FXML
  private void playVideo(ActionEvent event) {
    mediaPlayer.play();
    mediaPlayer.setRate(1.0D);
  }
  
  @FXML
  private void fastVideo(ActionEvent event) {
    mediaPlayer.setRate(1.5D);
  }
  
  @FXML
  private void fasterVideo(ActionEvent event) {
    mediaPlayer.setRate(2.0D);
  }
  
  @FXML
  private void slowVideo(ActionEvent event) {
    mediaPlayer.setRate(0.75D);
  }
  
  @FXML
  private void slowerVideo(ActionEvent event) { mediaPlayer.setRate(0.5D); }
  
  public void initialize(URL url, ResourceBundle rb) {}
}
