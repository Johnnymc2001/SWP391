/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Date;

/**
 *
 * @author JohnnyMC
 */
public class Ultilities {
    public static Date getCurrentDateAsSQLDate() {
        return new Date(System.currentTimeMillis());
    }
}
