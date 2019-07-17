/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientCptRates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utilities.Constants;
import utilities.Database;

/**
 *
 * @author FurqanIqbal
 */
public class ClientCptRatesHandler {

    public List<ClientCptRatesBo> selectForIvm(String completeOrderNo) {

        String[] columns = {"",
            "SUM_PAYABLE_AMOUNT", "SUM_REFUND_AMOUNT", "SUM_BALANCE_AMOUNT",
            "SUM_COMPLETE_ORDER_NO",};

        String query = "SELECT SUM(IVD.PAYABLE_AMOUNT)   SUM_PAYABLE_AMOUNT, \n"
                + " SUM(IVD.REFUND_AMOUNT)               SUM_REFUND_AMOUNT,  \n"
                + "  SUM(IVD.BALANCE_AMOUNT)             SUM_BALANCE_AMOUNT, \n"
                + "  IVD.COMPLETE_ORDER_NO               SUM_COMPLETE_ORDER_NO\n"
                + "  FROM"
                + Database.DCMS.invoiceDetail + " IVD,                      \n"
                + " WHERE IVD.COMPLETE_ORDER_NO = '001001190109424'\n"
                + " GROUP BY IVD.COMPLETE_ORDER_NO                          \n";

        System.out.println(query);
        List<HashMap> list = Constants.dao.selectDatainList(query, columns);
        List<ClientCptRatesBo> listVisit = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = list.get(i);
            ClientCptRatesBo selectobj = new ClientCptRatesBo();

            selectobj.setSumPayablelAmount((String) map.get("SUM_PAYABLE_AMOUNT"));
            selectobj.setSumRefundAmount((String) map.get("SUM_REFUND_AMOUNT"));
            selectobj.setSumBlanceAmount((String) map.get("SUM_BALANCE_AMOUNT"));
            selectobj.setCompleteOrderNo((String) map.get("SUM_COMPLETE_ORDER_NO"));
            
            listVisit.add(selectobj);
        }
        return listVisit;
    }
    
    
      public List<ClientCptRatesBo>selectForIvd(String toDate, String fromDate){

        String[] columns = {"",
            "COMPLETE_ORDER_NO", "ORDER_DETAIL_ID", "INVOICE_NO",
            "STATUS_ID","CPT_ID","CPT_NAME","UPDATE_PRICE","PRICE",
            "PAYABLE_AMOUNT","BALANCE_AMOUNT","REFUND_AMOUNT","TOTAL_AMOUNT"};

                
                
String query = "SELECT IVD.COMPLETE_ORDER_NO         COMPLETE_ORDER_NO,     \n" 
        + "   IVD.ORDER_DETAIL_ID,                   ORDER_DETAIL_ID,       \n" 
        + "   IVD.INVOICE_NO                         INVOICE_NO,            \n" 
        + "   IVD.STATUS_ID                          STATUS_ID ,            \n" 
        + "   IVD.CPT_ID                             CPT_ID,                \n" 
        + "   CP.DESCRIPTION                         CPT_NAME,              \n" 
        + "   CP.SAVE_COST                           UPDATE_PRICE,          \n" 
        + "   IVD.PRICE                              PRICE,                 \n" 
        + "   IVD.PAYABLE_AMOUNT                     PAYABLE_AMOUNT,        \n" 
        + "   IVD.BALANCE_AMOUNT                     BALANCE_AMOUNT,        \n"
        + "   IVD.REFUND_AMOUNT                      REFUND_AMOUNT ,        \n" 
        + "   IVD.TOTAL_AMOUNT                       TOTAL_AMOUNT           \n" 
        + "   FROM "
        + Database.DCMS.invoiceDetail + " IVD,                              \n"
        + Database.DCMS.invoiceMaster + " IVM,                              \n"
        + Database.DCMS.CPT + " CP,                                         \n"
        
        + "  WHERE IVD.TRN_DATE BETWEEN '01-JUL-19' AND '01-JUL-19'         \n" 
        + "  AND IVD.DEPARTMENT_ID = '127'                                  \n" 
        + "  AND IVM.PAYMENT_MODE_ID = '62'                                 \n" 
        + "  AND IVD.COMPLETE_ORDER_NO = IVM.COMPLETE_ORDER_NO              \n" 
        + "  AND IVD.CPT_ID = CP.CPT_ID                                     \n";
               
        List<HashMap> list = Constants.dao.selectDatainList(query, columns);
        List<ClientCptRatesBo> listVisit = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = list.get(i);
            ClientCptRatesBo selectobj = new ClientCptRatesBo();

            selectobj.setCompleteOrderNo((String) map.get("COMPLETE_ORDER_NO"));
            selectobj.setOrderDetailId((String) map.get("ORDER_DETAIL_ID"));
            selectobj.setInvoiceNo((String) map.get("INVOICE_NO"));
            selectobj.setStatusId((String) map.get("STATUS_ID"));
            selectobj.setCptId((String) map.get("CPT_ID"));
            selectobj.setCptName((String) map.get("CPT_NAME"));
            selectobj.setPrice((String) map.get("PRICE"));
            selectobj.setUpdatePrice((String) map.get("UPDATE_PRICE"));
            selectobj.setPayablelAmount((String) map.get("PAYABLE_AMOUNT"));
            selectobj.setBlanceAmount((String) map.get("BALANCE_AMOUNT"));
            selectobj.setRefundAmount((String) map.get("REFUND_AMOUNT"));
            selectobj.setTotalAmount((String) map.get("TOTAL_AMOUNT"));
            
            listVisit.add(selectobj);
        }
        return listVisit;
    }

}

