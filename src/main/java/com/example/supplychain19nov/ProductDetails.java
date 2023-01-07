package com.example.supplychain19nov;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    // TableView is a component that is used to create a table populate it, and remove items from it.
    public TableView<Product> productTable; // if we create productTable here only then on
    // every click of search button a new Table will be Recursively added

    public Pane getAllProducts(){
        // created column in table to display items
        TableColumn id = new TableColumn("Id"); // this will create column for id
        id.setCellValueFactory(new PropertyValueFactory<>("id")); // this is the actual id from Product class

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        // ObservableList is Interface and FXCollections is class
        // ObservableList is similar to ArrayList
        final ObservableList<Product> data = FXCollections.observableArrayList();
        // added dummy data into Table
        data.add(new Product(1, "Lenovo", 38999));
        data.add(new Product(2, "Mac", 99999));
        data.add(new Product(3, "Asus Tuf", 59999));

        ObservableList<Product> items = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setStyle(" -fx-background-color: blue;");
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setItems(items); // added all items of observable list into productTable
        productTable.getColumns().addAll(id, name, price); // added all columns into productTable
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);

        Pane tablePane = new Pane(); // created new pane
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.getChildren().add(productTable);// added productTable into Pane
        return tablePane;
    }

    public Pane getProductByName(String searchName){
        TableColumn id = new TableColumn("Id"); // this will create column for id
        id.setCellValueFactory(new PropertyValueFactory<>("id")); // this is the actual id from Product class

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        ObservableList<Product> items = Product.getProductsByName(searchName);

        productTable = new TableView<>();
        productTable.setStyle(" -fx-background-color: blue;");

        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setItems(items); // added all items of observable list into productTable
        productTable.getColumns().addAll(id, name, price); // added all columns into productTable
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);

        Pane tablePane = new Pane(); // created new pane
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.getChildren().add(productTable);// added productTable into Pane
        return tablePane;
    }
    // to place order on selected item
    public Product getSelectedProduct(){
        if(productTable == null){
            System.out.println("Table object not found");
            return null;
        }

        if(productTable.getSelectionModel().getSelectedIndex() != -1){
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId() + " " +
                    selectedProduct.getName() + " " +
                    selectedProduct.getPrice());
            return selectedProduct;
        }
        else{
            System.out.println("Nothing");
            return null;
        }

    }
}
