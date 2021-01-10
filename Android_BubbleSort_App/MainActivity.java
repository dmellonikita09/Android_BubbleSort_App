package com.example.bubblesort;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.view.View;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //global
    EditText unsortedNumbers;
    EditText multiNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unsortedNumbers = findViewById(R.id.unsortedNumbers);
        multiNumbers = findViewById(R.id.multiNumbers);
    }

    public void sortButtonPressed(View view){
        String[] numberList = unsortedNumbers.getText().toString().split(" ");
        Integer[] numbers = new Integer[numberList.length];
        if(numbers.length < 3){
            //tell them to add more numbers msg
            new AlertDialog.Builder(this)
                    .setTitle("Too few numbers")
                    .setMessage("Please add more numbers. The minimum input size is 3 numbers.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }//end if
        else if(numbers.length > 9){
            //tell them less numbers msg
            new AlertDialog.Builder(this)
                    .setTitle("Too many numbers")
                    .setMessage("There are too many numbers. Max input numbers is 8.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }//endifelse
        else {
            for (int i = 0; i < numberList.length; i++) {
                numbers[i] = Integer.parseInt(numberList[i]);
                if(numbers[i] > 9){
                    new AlertDialog.Builder(this)
                            .setTitle("Invalid Number.")
                            .setMessage("You have entered an invalid number. Valid numbers are  0 - 9. Please renter numbers.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) { }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    //should it erase the whole line or leave it?
                    //unsortedNumbers.setText("");
                    return;
                }
            }//end for
            BubbleSort(numbers, 0);
        }//end else
    }//end sortButtonPressed

    private void BubbleSort(Integer[] numbers, int startIndex){
        //print before it changes
        printArray(numbers, startIndex, -1, -1 );
        if(startIndex > numbers.length ){
            return;
        }
        for(int i = numbers.length-1; i > startIndex; i--){

            if(numbers[i] < numbers[i-1]){
                //underline whats going to swap
                //multiNumbers.append((Html.fromHtml(("<u>" + numbers[i].toString() + " " + numbers[i-1].toString()  + "</u>"))));
                printArray(numbers, startIndex, i , i-1);
                //swap numbers
                Integer temp = numbers[i];
                numbers[i] = numbers[i-1];
                numbers[i-1] = temp;
                //print the swap

            }
            //else
            //    multiNumbers.append(numbers[i].toString());
        }//end for
        //call bubble sort again
        multiNumbers.append("\n");
        BubbleSort(numbers, startIndex + 1);
    }

    private void printArray(Integer [] numbers, int startIndex, int currentIndex, int previousIndex){
        //String str = multiNumbers.getText().toString();
        //String num = Arrays.toString(numbers);
        //str = str + "\n " + num;
        String strBold = "";
        String strBoldCurrent = "";
        String strPreviousUnderline = "";
        for(int i = 0; i < numbers.length; i++){
            //what has been sorted?
            if(i < startIndex){
                //str = str + numbers[i].toString() + " ";
                strBold = "<font color='red'><b>" + numbers[i].toString() + "</b></font>";
                multiNumbers.append((Html.fromHtml(strBold + " ")));
                //str = str + strBold + " ";
            }
            else if(i == currentIndex){
                strBoldCurrent = "<u><b>" + numbers[i].toString() + "</b></u>";
                multiNumbers.append((Html.fromHtml(strBoldCurrent + " ")));
            }
            else if(i == previousIndex){
                strPreviousUnderline = "<u>" + numbers[i].toString()+ "</u>";
                multiNumbers.append((Html.fromHtml(strPreviousUnderline + " ")));
            }
            else
                multiNumbers.append(numbers[i].toString() + " ");

        }
        multiNumbers.append("\n");
        //multiNumbers.setText(str);
    }

    public void resetBtnClick(View view){
        unsortedNumbers.setText("");
        multiNumbers.setText((""));
    }

    public void quitApplication(View view){
        finish();
        System.exit(0);
    }

}