package com.mycompany.jmavsimtelemetrics;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable, IMessageReceiver {
    
    @FXML private Label RollAngle;
    @FXML private Label PitchAngle;
    @FXML private Label YawAngle;
    @FXML private Label RollSpeed;
    @FXML private Label PitchSpeed;
    @FXML private Label YawSpeed;
    
    @FXML private Label GlobalAltitude;
    @FXML private Label GlobalAltitudeGround;
    @FXML private Label GroundXSpeed;
    @FXML private Label GroundYSpeed;
    @FXML private Label GroundZSpeed;
    
    @FXML private Label XPosition;
    @FXML private Label YPosition;
    @FXML private Label ZPosition;
    @FXML private Label XSpeed;
    @FXML private Label YSpeed;
    @FXML private Label ZSpeed;
    
    @FXML private Label TargetXPosition;
    @FXML private Label TargetYPosition;
    @FXML private Label TargetZPosition;
    @FXML private Label TargetXVelocity;
    @FXML private Label TargetYVelocity;
    @FXML private Label TargetZVelocity;
    @FXML private Label XAcceleration;
    @FXML private Label YAcceleration;
    @FXML private Label ZAcceleration;

    @Override
    public void ReceiveMessage(Message message) {
        switch(message.getMessageId()) {
            case 85: setTargetLocalPosition(message);
            break;
            case 30: setAttitude(message);
            break;
            case 32: setLocalPosition(message);
            break;
            case 33: setGlobalPosition(message);
            break;
        }
    }
    
    private void setTargetLocalPosition(final Message message) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                TargetXPosition.setText(Float.toString(message.getFloat(7)));
                TargetYPosition.setText(Float.toString(message.getFloat(11)));
                TargetZPosition.setText(Float.toString(message.getFloat(15)));
                TargetXVelocity.setText(Float.toString(message.getFloat(19)));
                TargetYVelocity.setText(Float.toString(message.getFloat(23)));
                TargetZVelocity.setText(Float.toString(message.getFloat(27)));
                XAcceleration.setText(Float.toString(message.getFloat(31)));
                YAcceleration.setText(Float.toString(message.getFloat(35)));
                ZAcceleration.setText(Float.toString(message.getFloat(39)));
            }
        });
    }
    
    private void setAttitude(final Message message) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                RollAngle.setText(Float.toString(message.getFloat(4)));
                PitchAngle.setText(Float.toString(message.getFloat(8)));
                YawAngle.setText(Float.toString(message.getFloat(12)));
                RollSpeed.setText(Float.toString(message.getFloat(16)));
                PitchSpeed.setText(Float.toString(message.getFloat(20)));
                YawSpeed.setText(Float.toString(message.getFloat(24)));
            }
        });
    }
    
    private void setLocalPosition(final Message message) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                XPosition.setText(Float.toString(message.getFloat(4)));
                YPosition.setText(Float.toString(message.getFloat(8)));
                ZPosition.setText(Float.toString(message.getFloat(12)));
                XSpeed.setText(Float.toString(message.getFloat(16)));
                YSpeed.setText(Float.toString(message.getFloat(20)));
                ZSpeed.setText(Float.toString(message.getFloat(24)));
            }
        });
    }
    
    private void setGlobalPosition(final Message message) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                GlobalAltitude.setText(Float.toString(message.getInt(12)));
                GlobalAltitudeGround.setText(Float.toString(message.getInt(16)));
                GroundXSpeed.setText(Float.toString(message.getInt16(20)));
                GroundYSpeed.setText(Float.toString(message.getInt16(22)));
                GroundZSpeed.setText(Float.toString(message.getInt16(24)));
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
