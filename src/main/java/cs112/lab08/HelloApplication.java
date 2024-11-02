package cs112.lab08;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloApplication extends Application {

    // CONSTANTS
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    private static final LoteriaCard SPECIAL_CARD = new LoteriaCard("EChale Stem", "0.png", 0);

    // Keep track of drawn cards
    private final List<LoteriaCard> drawnCards = new ArrayList<>();
    private int drawCount = 0;

    @Override
    public void start(Stage stage) throws IOException {
        // Creating the VBox layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));

        // Title Label
        Label titleLabel = new Label("Welcome to EChALE STEM Loteria!");
        titleLabel.setFont(new Font("Arial", 20));

        // ImageView for displaying the Loteria card
        ImageView cardImageView = new ImageView();
        cardImageView.setFitHeight(250);
        cardImageView.setFitWidth(200);

        // Message Label
        Label messageLabel = new Label("Draw a card to start the game!");
        messageLabel.setFont(new Font("Arial", 14));

        // Button for drawing a new card
        Button drawCardButton = new Button("Draw Card");

        // Add components to the VBox
        vbox.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton);

        // Setting up the Scene and Stage
        Scene scene = new Scene(vbox, 350, 500);
        stage.setTitle("EChALE STEM Loteria");
        stage.setScene(scene);
        stage.show();

        // Event Handling for Button
        drawCardButton.setOnAction(e -> {
            if (drawCount < LOTERIA_CARDS.length) {
                // Randomly select a Loteria card that hasn't been drawn
                int randomIndex;
                LoteriaCard selectedCard;
                do {
                    randomIndex = new Random().nextInt(LOTERIA_CARDS.length);
                    selectedCard = LOTERIA_CARDS[randomIndex];
                } while (drawnCards.contains(selectedCard));

                // Update ImageView and Message Label
                cardImageView.setImage(selectedCard.getImage());
                messageLabel.setText("You drew: " + selectedCard.getCardName());

                // Add selected card to drawn list and increment count
                drawnCards.add(selectedCard);
                drawCount++;
            } else {
                // Show the special card after all have been drawn
                cardImageView.setImage(SPECIAL_CARD.getImage());
                messageLabel.setText("Game Over, All cards have been drawn!");
                drawCardButton.setDisable(true);  // Disable the button
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
