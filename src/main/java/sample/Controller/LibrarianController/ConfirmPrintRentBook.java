package sample.Controller.LibrarianController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmPrintRentBook {

    public static void display()
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm");
        window.setMinWidth(250);

        Label messageContent = new Label("Bạn có muốn in phiếu mượn không");


        Button noBtn = new Button("Không");
        Button yesBtn = new Button("Có");


        noBtn.setOnAction(e -> window.close());
        yesBtn.setOnAction(e -> {
            RentBookController.printRentBook();
            window.close();
        });

        VBox layout = new VBox(10);
        HBox buttonContainer = new HBox(10);

        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(yesBtn, noBtn);

        layout.setAlignment(Pos.CENTER);
        layout.paddingProperty().setValue(new Insets(10, 10, 5, 10));
        layout.getChildren().addAll(messageContent, buttonContainer);

        window.setScene(new Scene(layout));
        window.show();

    }
}
