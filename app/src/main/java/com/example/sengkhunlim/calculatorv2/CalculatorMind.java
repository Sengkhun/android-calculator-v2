package com.example.sengkhunlim.calculatorv2;

public class CalculatorMind {

    private Double preResult;
    private String currentDisplay, description, lastOp;
    private Boolean clear, pressed;

    public CalculatorMind() {

        preResult = 0.0;
        currentDisplay = description = "0";
        lastOp = "";
        clear = pressed = false;

    }

    // Function that handle the operator
    public String operation( String op ) {

        if ( !pressed )
            preResult = calculate( preResult, Double.parseDouble( currentDisplay ), lastOp );

        handleDescription(op);

        lastOp = op;
        clear = pressed = true;
        currentDisplay = fmt( preResult );

        if ( op.equals( "=" ) ) {

            preResult = 0.0;
            pressed = false;

        }

        return currentDisplay;

    }

    public void handleDescription(String op) {

        // if the description = 0, we set it to empty
        if ( description.equals( "0" ) )
            description = "";

        if ( !lastOp.equals( "=" ) ) {

            if ( pressed ) {

                // Delete the last operation in description
                description = description.substring(0, description.length() - 3);

            } else {

                description += currentDisplay;

            }

            if ( !op.equals( "=" ) )
                description += " " + op + " ";

        } else {

            description = currentDisplay + " " + op + " ";

        }

    }

    // Function that do the operation
    public Double calculate( Double n1, Double n2, String op ) {

        switch ( op ) {

            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;

            case "x":
                return n1 * n2;

            case "/":
                return n1 / n2;

            case "%":
                return n1 % n2;

        }

        return n1 + n2;

    }

    // Function that handle the button number
    public String typeDigit( String digit ) {

        String newTextInDisplay;
        pressed = false;

        // if the user click =, we reset the description as empty
        if (lastOp.equals("="))
            this.description = "0";

        if ( currentDisplay.equals( "0" ) )
            currentDisplay = "";

        if ( digit.equals( "0" ) && currentDisplay.equals( "0" ) ) {

            newTextInDisplay = "0";

        } else if ( clear ) {

            newTextInDisplay = digit;
            clear = false;

        } else {

            newTextInDisplay = currentDisplay + digit;

        }

        currentDisplay = newTextInDisplay;
        return currentDisplay;

    }

    public String getDescription() {
        return this.description;
    }

    // Function that handle the dot sign
    public String dot() {

        clear = false;

        if ( pressed || lastOp.equals( "=" ) )
            currentDisplay = "0.";

        if ( !currentDisplay.contains( "." ) )
            currentDisplay += ".";

        return currentDisplay;

    }

    // Function that clear the display
    public String clear() {

        preResult = 0.0;
        currentDisplay = "0";
        description = "0";
        lastOp = "";
        clear = pressed = false;

        return currentDisplay;

    }

    // Function that delete the last digit
    public String del( String currentText ) {

        if ( currentText.length() <= 1 )
            currentDisplay = "0";
        else
            currentDisplay = currentText.substring( 0, currentText.length() - 1 );

        return currentDisplay;

    }

    // Function that handle the fraction to show or not
    public String fmt(double d)
    {

        if ( d == (long) d )
            return String.format( "%d", (long) d );
        else
            return String.format( "%s", d );

    }

}
