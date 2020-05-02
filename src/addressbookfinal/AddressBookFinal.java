/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbookfinal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Pawan
 */
public class AddressBookFinal extends Application {
      // Declearing
        Button add = new Button("Add");
        Button next = new Button("Next");
        Button previous = new Button("Previous");
        Button last = new Button("Last");
        Button first = new Button("First");
        
        Label labelName = new Label("Name");
        Label labelStreet = new Label("Street");
        Label labelCity = new Label("City");
        Label labelState = new Label("State");
        Label labelZip = new Label("Zip Code");
        
        TextField name = new TextField();
        TextField street = new TextField();
        TextField city = new TextField();
        TextField state = new TextField();
        TextField zip = new TextField();
        
        
        AddressBook addressBook = new AddressBook();
        
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        
        RandomAccessFile addressbook = new RandomAccessFile("AddressBook.dat","rw");
        
        addressbook.writeBytes("name,street,city,state,zip code\r\n");
        
      
        //labeling
        labelName.setLabelFor(name);
        labelStreet.setLabelFor(street);
        labelCity.setLabelFor(city);
        labelState.setLabelFor(state);
        labelZip.setLabelFor(zip);
        
//        labelName.setMnemonicParsing(true);
//        labelStreet.setMnemonicParsing(true);
//        labelCity.setMnemonicParsing(true);
//        labelState.setMnemonicParsing(true);
//        labelZip.setMnemonicParsing(true);
        //Aligning
        add.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                addressBook.add();
            }
        });
        
        first.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                   addressBook.First();
            }
        });
        
        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addressBook.previous();
                } catch (IOException ex) {
                    Logger.getLogger(AddressBookFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addressBook.next();
                } catch (IOException ex) {
                    Logger.getLogger(AddressBookFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         last.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {try {
                    addressBook.last();
                } catch (IOException ex) {
                    Logger.getLogger(AddressBookFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
        
        
        
        
    Pane root = new Pane();
    
    add.setLayoutX(0);
    add.setLayoutY(220);
    
    first.setLayoutX(50);
    first.setLayoutY(220);
    
    next.setLayoutX(100);
    next.setLayoutY(220);
    
    previous.setLayoutX(150);
    previous.setLayoutY(220);
    
    last.setLayoutX(220);
    last.setLayoutY(220);
    
    root.getChildren().addAll(add,first,next,previous,last);
    
    name.setLayoutX(70);
    name.setLayoutY(10);
    
    street.setLayoutX(70);
    street.setLayoutY(50);
    
    city.setLayoutX(70);
    city.setLayoutY(90);
    
    state.setLayoutX(70);
    state.setLayoutY(130);
    
    zip.setLayoutX(70);
    zip.setLayoutY(170);
    
    root.getChildren().addAll(name,street,city,state,zip);
    
    labelName.setLayoutX(5);
    labelName.setLayoutY(10);
    
    labelStreet.setLayoutX(5);
    labelStreet.setLayoutY(50);
    
    labelCity.setLayoutX(5);
    labelCity.setLayoutY(90);
    
    labelState.setLayoutX(5);
    labelState.setLayoutY(130);
    
    labelZip.setLayoutX(5);
    labelZip.setLayoutY(170);
    
    root.getChildren().addAll(labelName,labelStreet,labelCity,labelState,labelZip);
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
 class AddressBook {
    private int Name=32;
    private int Firstcount=0, lineNumber;
    private  long CursorPosition;
    
    public AddressBook(){};
    
    
    void showDialogBox(){
        Alert alert = new Alert(AlertType.INFORMATION, "No More Records", ButtonType.CLOSE);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.CLOSE) {
            alert.close();
        }
    }
    
    public void add(){
        try ( // Create a random access file
			RandomAccessFile inout = 
				new RandomAccessFile("AddressBook.dat", "rw");
		) {
            
			inout.seek(inout.length());
		 	write(inout);
                        Firstcount++;
                        
		}
		catch (FileNotFoundException ex) {}
		catch (IOException | IndexOutOfBoundsException ex) {}
        
                name.setText("");
                street.setText("");
                city.setText("");
                state.setText("");
                zip.setText("");      
    }
    
    
    public  void write(RandomAccessFile inout) throws IOException{
        
        inout.writeBytes(name.getText()+","+street.getText()+","+city.getText()+","+state.getText()+","+zip.getText()+"\r\n");
        
    }
    
    void showInfo(String completeLine) throws IOException{
        
        if(completeLine !=null){
                String[] info = completeLine.split(",");
                String nameStr  = info[0];
                String streetStr = info[1];
                String cityStr = info[2];
                String stateStr = info[3];
                String zipStr = info[4];
                
                name.setText(nameStr);
                street.setText(streetStr);
                city.setText(cityStr);
                state.setText(stateStr);
                zip.setText(zipStr); 
        }
           }
    
    public void First(){
        try ( // Create a random access file
			RandomAccessFile inout = 
				new RandomAccessFile("AddressBook.dat", "rw");         
		) {if(inout.length()>0){
                    lineNumber=0;
                    CursorPosition =getCursorPosition(lineNumber);
			inout.seek(CursorPosition);
                
                        //System.out.println(read(inout));  
                        showInfo(read(inout));}
                
		}
		catch (IOException | IndexOutOfBoundsException ex) {}
    }
    
    public void next() throws FileNotFoundException, IOException {

        
            RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rws");
            CursorPosition = getCursorPosition(lineNumber+1);
            inout.seek(CursorPosition);
           String line = read(inout);
            if(line!=null){
            lineNumber++;}
           
            showInfo(line);
	}

    
    long getCursorPosition(int lineNumber) throws FileNotFoundException, IOException{
        
        RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        long lineBreakCount = 0;
        long cursorPosition=33;
        
        while(lineBreakCount < lineNumber){            
            inout.seek(cursorPosition);
            inout.readLine();            
            cursorPosition=inout.getFilePointer();
            lineBreakCount++;           
        }
        return cursorPosition;
    }

	/** Read the previous Address from the file */
	public void previous() throws FileNotFoundException, IOException {
		
           
            RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rws");
            CursorPosition = getCursorPosition(lineNumber-1);
            inout.seek(CursorPosition);String line = read(inout);
            if(line!=null){
            lineNumber--;}
           
            showInfo(line);
	}

	/** Read last address from file */
	public void last() throws FileNotFoundException, IOException {
            RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rws");
            lineNumber=0;
            while(CursorPosition<inout.length()){
                lineNumber++;
                CursorPosition=getCursorPosition(lineNumber);
                inout.seek(CursorPosition);
                read(inout);
            }
            lineNumber--;
            CursorPosition = getCursorPosition(lineNumber);
            inout.seek(CursorPosition);
            
            showInfo(read(inout));
	}

	
    public String read(RandomAccessFile inout) throws IOException{
        return inout.readLine();
    }   
}

}

