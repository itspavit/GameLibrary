module ca.ucalgary.cpsc.projectgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.projectgui to javafx.fxml;
    exports ca.ucalgary.projectgui;
}