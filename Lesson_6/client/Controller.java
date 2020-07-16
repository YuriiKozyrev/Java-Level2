package Lesson_6.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {
    @FXML
    ListView<VBox> textAreaListView;

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    ListView<String> clientList;


    private boolean isAuthorized;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
            textAreaListView.setVisible(false);
            textAreaListView.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
            textAreaListView.setVisible(true);
            textAreaListView.setManaged(true);
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {

        if(socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Label label = new Label(str + "\n");
                                        VBox vBox = new VBox();
                                        vBox.getChildren().add(label);
                                        textAreaListView.getItems().add(vBox);
                                    }
                                });
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/")) {
                                if (str.equals("/serverclosed")) {
                                    // closeApp();
                                    break;
                                }
                                if (str.startsWith("/clientlist ")) {
                                    String[] tokens = str.split(" ");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            clientList.getItems().clear();
                                            for (int i = 1; i < tokens.length; i++) {
                                                clientList.getItems().add(tokens[i]);
                                            }
                                        }
                                    });
                                }
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isStrMine(str)) {
                                            String resultStr = str.substring(1);
                                            Label label = new Label(resultStr + "\n");
                                            VBox vBox = new VBox();
                                            vBox.setStyle("-fx-background-color: cornsilk");
                                            vBox.getChildren().add(label);
                                            vBox.setAlignment(Pos.TOP_RIGHT);
                                            textAreaListView.getItems().add(vBox);
                                        } else {
                                            Label label = new Label(str + "\n");
                                            VBox vBox = new VBox();
                                            vBox.getChildren().add(label);
                                            vBox.setAlignment(Pos.TOP_LEFT);
                                            textAreaListView.getItems().add(vBox);
                                        }
                                    }
                                });
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }

            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isStrMine(String str) {
        String firstSymbol = Character.toString(str.charAt(0));
        return firstSymbol.equals("*");
    }

//    @FXML
//    public void handleCloseButtonAction(ActionEvent event) {
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }



//    public void closeApp() {
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.exit(0);
//    }
}
