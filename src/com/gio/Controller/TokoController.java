package com.gio.Controller;

import com.gio.Entity.Category;
import com.gio.Entity.Item;
import com.gio.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


//PBO2P03-IgnatiusGiovan-1772003.java
//Aplikasi untuk toko kecil
//TokoController digunakan untuk mengontrol TokoView
public class TokoController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private TableView<Item> tabToko;
    @FXML
    private TableColumn<Item, String> cID;
    @FXML
    private TableColumn<Item, String> cName;
    @FXML
    private TableColumn<Item, String> cCategory;
    @FXML
    private TableColumn<Item, String> cExpDate;
    @FXML
    private Button update;
    public ObservableList<Category> categories;
    public ObservableList<Item> items;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private DatePicker dateExp = new DatePicker();
    @FXML
    private Button save;
    @FXML
    private Button reset;

    public ObservableList<Category> getCategories() {
        if(categories == null){
            categories = FXCollections.observableArrayList();
        }
        return categories;
    }

    public ObservableList<Item> getItems() {
        if(items == null){
            items = FXCollections.observableArrayList();
        }
        return items;
    }

    @FXML
    private void categoryForm(ActionEvent actionEvent){ //Untuk menghubungkan ke CategoryController
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/CategoryManagementView.fxml"));
            VBox root = loader.load();
            CategoryController controller=loader.getController();
            controller.setTokoController(this);
            Stage mainStage = new Stage();
            mainStage.initModality(Modality.WINDOW_MODAL);
            mainStage.setTitle("Category Management");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) { //Void untuk save data yang telah dimasukkan di textfield
        Item item = new Item();
        item.setIdItem(Integer.valueOf(txtId.getText().trim()));
        item.setNamaItem(txtName.getText());
        boolean ada1 = false;
        if(txtName.getText().isEmpty() || comboCategory.getValue() == null || txtId.getText().isEmpty()){
            alert.setContentText("Please fill name/ price/ category");
        } else{
            for (Item i : items){
                if(i.getNamaItem().equals(item.getNamaItem())){
                    ada1 = true;
                    alert.setContentText("Duplicate item found");
                    alert.showAndWait();
                    break;
                }
            }if(!ada1)
                item.setIdItem(Integer.valueOf(txtId.getText()));
                item.setNamaItem(txtName.getText());
                item.setCategory(comboCategory.getValue());
                item.setDate(dateExp.getValue());
                items.add(item);
            }
        }

    @FXML
    private void btnReset(ActionEvent actionEvent) {    //button untuk reset textfield
        comboCategory.setValue(null);
        txtName.setText("");
        txtId.setText("");
        save.setDisable(false);
        update.setDisable(true);
    }

    @FXML
    private void btnUpdate(ActionEvent actionEvent) {   //button untuk update data pada table
        save.setDisable(true);
        update.setDisable(false);
        Item item =tabToko.getSelectionModel().getSelectedItem();
        item.setNamaItem(txtName.getText());
        item.setIdItem(Integer.valueOf(txtId.getText().trim()));
        item.setDate(dateExp.getValue());
        item.setCategory(comboCategory.getValue());
        tabToko.refresh();
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabToko.setItems(getItems());
        update.setDisable(true);
        comboCategory.setItems(getCategories());
        cID.setCellValueFactory(data->{
            Item item = data.getValue();
            return new SimpleStringProperty(String.valueOf(item.getIdItem()));
        });
        cName.setCellValueFactory(data->{
            Item item = data.getValue();
            return new SimpleStringProperty(item.getNamaItem());
        });
        cCategory.setCellValueFactory(data->{
            Item item = data.getValue();
            return new SimpleStringProperty(String.valueOf(item.getCategory()));
        });
        cExpDate.setCellValueFactory(data->{
            Item item = data.getValue();
            return new SimpleStringProperty(String.valueOf(item.getDate()));
        });
    }
}
