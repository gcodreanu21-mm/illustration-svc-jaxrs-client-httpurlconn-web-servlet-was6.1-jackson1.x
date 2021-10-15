package client;

import com.mm.dts.illustration.svc.data.SalesIllustrationResult;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClientPega {

    public static void run() {

        try {
            java.net.URL url = new java.net.URL("http://blue-sky-docker-d3.private.massmutual.com:19690/IllustrationSvc/services/determineWhichApp");
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");            
            
//            com.mm.dts.illustration.svc.data.SalesIllustrationInput myObj = new com.mm.dts.illustration.svc.data.SalesIllustrationInput();
            com.mm.dts.illustration.svc.data.SalesIllustrationInput myObj = new com.mm.dts.illustration.svc.data.SalesIllustrationInput();

//            String setContractNumber = tools.getParamValue("ContractNumber");
//            String setAdmin = tools.getParamValue("AdminSys");
//            String setLOB = tools.getParamValue("LOB");
//            String setProdType = tools.getParamValue("ProdType");
//            String setKindCode = tools.getParamValue("KindCode");
//            String setBasis = tools.getParamValue("Basis");
//            String setRate = tools.getParamValue("Rate");
//            String setSeriesYear = tools.getParamValue("SeriesYear");
//            String setInitials = tools.getParamValue("Initials");
//            String setProducerID = tools.getParamValue("ProducerID");
//            String setAgentCode = tools.getParamValue("AgentCode");
//            String setFullName = tools.getParamValue("FullName");
//            String setLastName = tools.getParamValue("LastName");
//            String fieldNetURL = tools.getParamValue("FieldNetURL");
//            String ConversionDtls = tools.getParamValue("ConversionDtls");
//
//            myObj.setPolicyNumber(setContractNumber);
//            myObj.setAdmin(setAdmin);
//            myObj.setLineOfBusiness(setLOB);
//            myObj.setProdType(setProdType);
//            myObj.setKindCode(setKindCode);
//            myObj.setBasis(setBasis);
//            myObj.setRate(setRate);
//            myObj.setSeriesYear(Integer.parseInt(setSeriesYear));
//            myObj.setInitials(setInitials);
//            myObj.setProducerID(setProducerID);
//            myObj.setAgentCode(setAgentCode);
//            myObj.setFullName(setFullName);
//            myObj.setLastName(setLastName);
//            myObj.setConvPolType(ConversionDtls);

            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
            java.lang.String inputJsonString = mapper.writeValueAsString(myObj);
            
//            com.mm.dts.illustration.svc.ejb.SalesIllustrationUtilEJBHome home = (com.mm.dts.illustration.svc.ejb.SalesIllustrationUtilEJBHome) javax.rmi.PortableRemoteObject.narrow(com.mm.framework.dor.DORService.findHome("SalesIllustrationUtilRemote"),com.mm.dts.illustration.svc.ejb.SalesIllustrationUtilEJBHome.class);
//            com.mm.dts.illustration.svc.ejb.SalesIllustrationUtilRemote ejb = home.create();
//            com.mm.dts.illustration.svc.data.SalesIllustrationResult res = ejb.determineWhichApp(myObj);

            java.io.OutputStream os = conn.getOutputStream();
            os.write(inputJsonString.getBytes());
            os.flush();

            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader((conn.getInputStream())));

            com.mm.dts.illustration.svc.data.SalesIllustrationResult res = new com.mm.dts.illustration.svc.data.SalesIllustrationResult();
            java.lang.String outputJsonString;
            while ((outputJsonString = br.readLine()) != null) {
                res = mapper.readValue(outputJsonString, com.mm.dts.illustration.svc.data.SalesIllustrationResult.class);
            }
            conn.disconnect();
            
//            pgTopPage.putString("SalesIllustrationsLinks", fieldNetURL + (String)res.getIllustrationAppLink());
//            pgTopPage.putString("Message1", (String)res.getMessage());
//            pgTopPage.putString("Message2", (String)res.getMessage2());

            System.out.println("SalesIllustrationsLinks" + (String)res.getIllustrationAppLink());
            System.out.println("Message1" + (String)res.getMessage());
            System.out.println("Message2" + (String)res.getMessage2());

//        } catch (Exception e) {
//            e.printStackTrace();
//            pgTopPage.putString("ServiceMessages","Failed to get Sales Illustrations Links" + e.toString());
//        }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServiceMessages" + "Failed to get Sales Illustrations Links" + e.toString());
        }
    }

    public static void main(String args[]) {
        run();
    }
}
