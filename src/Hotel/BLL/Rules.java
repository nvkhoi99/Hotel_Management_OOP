/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

/**
 *
 * @author Yue
 */
public class Rules {

    public static boolean StateIsChanged = false;

    public static final int NO_DEPOSIT = 0;
    public static final int DEPOSITED = 1;
    public static final int CHECKED_IN = 2;
    public static final int CANCELED = 3;

    public static final int STANDARD_CHECK_IN_HOUR = 14;
    public static final int STANDARD_CHECK_OUT_HOUR = 12;
    public static final int MAX_EARLY_CHECK_IN_HOUR = 5;
    public static final int MAX_LATELY_CHECK_OUT_HOUR = 18;

    public static final int SURCHARGE_EARLY_PERCENT = 30;
    public static final int SURCHARGE_LATELY_PERCENT = 30;
}
