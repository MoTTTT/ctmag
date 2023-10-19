//**************************************************************
// Handler to fill out the magcurvetest table in the report. Only
// fill in the fields for tests that have been assigned to a phase.
//**************************************************************

import java.lang.*;
public class KneePointTableHandler extends java.lang.Object
{
    private Ctmag ctmag;
    
    private final int SEVENTY = 70;
    private final int HUNDRED = 100;
    private final int PERCENTAGE_INC = 5;
    private final int TABLE_SIZE = 13;
    
    
    public KneePointTableHandler(Ctmag c)
    {
       ctmag = c;      
    }
    
//**************************************************************
// Method gets called when a test is imported. Only fill in the 
// table for the tests that have been assigned to a phase. 
//**************************************************************
    
    public void doThreePhaseTable()
    {
        int temp;
        int volt_mul_index = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.VOLT_MUL;
        int amp_mul_index = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.AMP_MUL;
        int conversion_factor = ctmag.testcontrolengine.CONVERSION_FACTOR;
        
        for(int q = ctmag.magcurvetest.RED; q <= ctmag.magcurvetest.BLUE; q++)
        {
            temp = ctmag.magcurvetest.vimagcurve[q].knee.getX();
            if(temp > 0)
            {
                try{
                    doPhaseTable(temp ,q , ctmag.magcurvetest.vimagcurve[q].data[volt_mul_index],
                        ctmag.magcurvetest.vimagcurve[q].data[amp_mul_index], conversion_factor);
                }
                catch (ArrayIndexOutOfBoundsException a)
                {
                    doPhaseTable(temp,q,ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR , 
                        ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR, conversion_factor);                    
                }
            }
            else
                clearTable(q);
        }            
    }
    
//**************************************************************
// Do not fill in a table for a phase with a 0 current reading
// at the kneepoint.
//**************************************************************
    
    public void doTable(int phase, int volt_factor, int amp_factor, int conversion_factor)
    {
        int temp;
        
        temp = ctmag.magcurvetest.vimagcurve[phase].knee.getX();
        if(temp > 0)
            doPhaseTable(temp,phase,volt_factor,amp_factor,conversion_factor);
    }

//**************************************************************
// Enter the values for the specified phase into the report table.
//**************************************************************
    
    public void doPhaseTable(int knee_current, int phase, int volt_factor, int amp_factor, int conversion_factor)
    {
        final int VOLTAGE = 0, CURRENT = 1;
        
        int temp_current, temp_voltage, percentage;
        int table_index[][];
         
        table_index = new int[TABLE_SIZE][2];
        percentage = 70;
      
         
        for(int i = 0; i < TABLE_SIZE; i++,percentage += PERCENTAGE_INC )
        {
            temp_current = knee_current * percentage / HUNDRED;
            temp_voltage = ctmag.magcurvetest.vimagcurve[phase].data[temp_current];
            temp_current = temp_current * amp_factor / conversion_factor;//!!!
            temp_voltage = temp_voltage * volt_factor / conversion_factor;   //!!!
            table_index[i][VOLTAGE] = temp_voltage;
            table_index[i][CURRENT] = temp_current;
        }
        
        switch(phase)
        {
            case ctmag.magcurvetest.RED:                                            
                    ctmag.kneepointtable.tablelabel_ab_v.setText("" + table_index[12][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ab_a.setText("" + table_index[12][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ab_iv.setText("" + table_index[11][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ab_ia.setText("" + table_index[11][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ac_v.setText("" + table_index[10][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ac_a.setText("" + table_index[10][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ac_iv.setText("" + table_index[9][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ac_ic.setText("" + table_index[9][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ad_v.setText("" + table_index[8][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ad_a.setText("" + table_index[8][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ad_iv.setText("" + table_index[7][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ad_ic.setText("" + table_index[7][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ae_v.setText("" + table_index[6][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ae_a.setText("" + table_index[6][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ae_iv.setText("" + table_index[5][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ae_ic.setText("" + table_index[5][CURRENT]);
                    ctmag.kneepointtable.tablelabel_af_v.setText("" + table_index[4][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_af_a.setText("" + table_index[4][CURRENT]);
                    ctmag.kneepointtable.tablelabel_af_iv.setText("" + table_index[3][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_af_ic.setText("" + table_index[3][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ag_v.setText("" + table_index[2][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ag_a.setText("" + table_index[2][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ag_iv.setText("" + table_index[1][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ag_ic.setText("" + table_index[1][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ah_v.setText("" + table_index[0][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ah_a.setText("" + table_index[0][CURRENT]);
                    break;
            case ctmag.magcurvetest.WHITE:
                    ctmag.kneepointtable.tablelabel_bb_v.setText("" + table_index[12][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bb_a.setText("" + table_index[12][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bb_iv.setText("" + table_index[11][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bb_ic.setText("" + table_index[11][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bc_v.setText("" + table_index[10][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bc_a.setText("" + table_index[10][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bc_iv.setText("" + table_index[9][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bc_ic.setText("" + table_index[9][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bd_v.setText("" + table_index[8][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bd_a.setText("" + table_index[8][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bd_iv.setText("" + table_index[7][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bd_ic.setText("" + table_index[7][CURRENT]);
                    ctmag.kneepointtable.tablelabel_be_v.setText("" + table_index[6][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_be_a.setText("" + table_index[6][CURRENT]);
                    ctmag.kneepointtable.tablelabel_be_iv.setText("" + table_index[5][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_be_ic.setText("" + table_index[5][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bf_v.setText("" + table_index[4][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bf_a.setText("" + table_index[4][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bf_iv.setText("" + table_index[3][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bf_ic.setText("" + table_index[3][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bg_v.setText("" + table_index[2][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bg_a.setText("" + table_index[2][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bg_iv.setText("" + table_index[1][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bg_ic.setText("" + table_index[1][CURRENT]);
                    ctmag.kneepointtable.tablelabel_bh_v.setText("" + table_index[0][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_bh_a.setText("" + table_index[0][CURRENT]);
                    break;
            case ctmag.magcurvetest.BLUE:
                    ctmag.kneepointtable.tablelabel_cb_v.setText("" + table_index[12][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cb_a.setText("" + table_index[12][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cb_iv.setText("" + table_index[11][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cb_ic.setText("" + table_index[11][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cc_v.setText("" + table_index[10][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cc_a.setText("" + table_index[10][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cc_iv.setText("" + table_index[9][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cc_ic.setText("" + table_index[9][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cd_v.setText("" + table_index[8][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cd_a.setText("" + table_index[8][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cd_iv.setText("" + table_index[7][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cd_ic.setText("" + table_index[7][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ce_v.setText("" + table_index[6][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ce_a.setText("" + table_index[6][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ce_iv.setText("" + table_index[5][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ce_ic.setText("" + table_index[5][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cf_v.setText("" + table_index[4][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cf_a.setText("" + table_index[4][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cf_iv.setText("" + table_index[3][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cf_ic.setText("" + table_index[3][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cg_v.setText("" + table_index[2][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cg_a.setText("" + table_index[2][CURRENT]);
                    ctmag.kneepointtable.tablelabel_cg_iv.setText("" + table_index[1][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_cg_ic.setText("" + table_index[1][CURRENT]);
                    ctmag.kneepointtable.tablelabel_ch_v.setText("" + table_index[0][VOLTAGE]);
                    ctmag.kneepointtable.tablelabel_ch_a.setText("" + table_index[0][CURRENT]);            
                    break;
            default:            
                    break;  
        }
    }
    
//**************************************************************
// Method to clear the fields in the reporttable for a specified
// phase.
//**************************************************************
    
    public void clearTable(int phase)
    {
        switch(phase)
        {
            
            case ctmag.magcurvetest.RED:
                    ctmag.kneepointtable.tablelabel_ab_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ab_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ab_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ab_ia.setText(" ");
                    ctmag.kneepointtable.tablelabel_ac_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ac_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ac_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ac_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ad_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ad_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ad_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ad_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ae_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ae_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ae_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ae_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_af_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_af_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_af_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_af_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ag_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ag_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ag_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ag_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ah_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ah_a.setText(" ");
                    break;                                            
            case ctmag.magcurvetest.WHITE:                                            
                    ctmag.kneepointtable.tablelabel_bb_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bb_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_bb_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_bb_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_bc_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bc_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_bc_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_bc_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_bd_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bd_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_bd_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_bd_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_be_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_be_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_be_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_be_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_bf_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bf_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_bf_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_bf_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_bg_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bg_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_bg_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_bg_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_bh_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_bh_a.setText(" ");
                    break;
            case ctmag.magcurvetest.BLUE:
                    ctmag.kneepointtable.tablelabel_cb_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_cb_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_cb_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_cb_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_cc_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_cc_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_cc_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_cc_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_cd_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_cd_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_cd_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_cd_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ce_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ce_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_ce_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_ce_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_cf_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_cf_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_cf_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_cf_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_cg_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_cg_a.setText(" ");
                    ctmag.kneepointtable.tablelabel_cg_iv.setText(" ");
                    ctmag.kneepointtable.tablelabel_cg_ic.setText(" ");
                    ctmag.kneepointtable.tablelabel_ch_v.setText(" ");
                    ctmag.kneepointtable.tablelabel_ch_a.setText(" ");        
                    break;
            default:
                        break;
        }
    }
}