package controller;

import dao.getdata;


import dao.stocks;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/")
public class getstockdata {
    private String[] tickers = {"B", "CX", "UBNK","ARES^A", "IESC", "GCAP", "ASUR","PKOH", "ROST", "LUK", "AGO^E", "EAGLW", "BANC^C", "ELGX", "MORN", "ZEN", "FENG", "TCS", "FIVE", "KGJI", "SPNE", "USB^H", "ESBK", "CRT", "CTSH", "AFT", "LINC", "ALL", "SHSP", "BLRX", "PEP", "XOXO","SOL", "USLB", "LOXO","AUBN","GHDX","GVA", "JAX", "FUSB", "DTRM", "BVXVW", "QQQX", "TTNP", "VIAV", "VREX","GATX", "BAM", "BDC","USAC","ESG", "DBD", "CDI", "FJP", "BOBE", "LPTH", "KBSF", "WHLRW", "SLM", "BIOP", "INST", "OMEX", "XHR", "AHL", "RFIL", "BJRI", "PCK","PRAA", "MSL", "KTOS","REXR^A", "CBD","WRI", "IDSY",  "SSW^D","GPT", "CSTM", "O", "PTR", "BG","CRIS", "REN", "BLUE", "KALU", "PLBC", "HII", "BKS", "SAB", "MMM", "INWK", "KT", "FDP", "MLAB", "AFSI^C", "MELI", "LAD", "WSBC", "MOH", "LEE", "CLRBZ"};
    private int flag = 0;
    private HashMap<String,String> stocksmap = new HashMap<>();

    {
        stocksmap.put("B", "Barnes Group, Inc.");
        stocksmap.put("CX", "Cemex S.A.B. de C.V.");
        stocksmap.put("UBNK", "United Financial Bancorp, Inc. ");
        stocksmap.put("ARES^A", "Ares Management L.P.");
        stocksmap.put("IESC", "IES Holdings, Inc.");
        stocksmap.put("GCAP", "GAIN Capital Holdings, Inc.");
        stocksmap.put("ASUR", "Asure Software Inc");
        stocksmap.put("PKOH", "Park-Ohio Holdings Corp.");
        stocksmap.put("ROST", "Ross Stores, Inc.");
        stocksmap.put("LUK", "Leucadia National Corporation");
        stocksmap.put("AGO^E", "Assured Guaranty Ltd.");
        stocksmap.put("EAGLW", "Double Eagle Acquisition Corp.");
        stocksmap.put("BANC^C", "Banc of California, Inc.");
        stocksmap.put("ELGX", "Endologix, Inc.");
        stocksmap.put("MORN", "Morningstar, Inc.");
        stocksmap.put("ZEN", "Zendesk, Inc.");
        stocksmap.put("FENG", "Phoenix New Media Limited");
        stocksmap.put("TCS", "Container Store (The)");
        stocksmap.put("FIVE", "Five Below, Inc.");
        stocksmap.put("KGJI", "Kingold Jewelry Inc.");
        stocksmap.put("SPNE", "SeaSpine Holdings Corporation");
        stocksmap.put("USB^H", "U.S. Bancorp");
        stocksmap.put("ESBK", "Elmira Savings Bank NY (The)");
        stocksmap.put("CRT", "Cross Timbers Royalty Trust");
        stocksmap.put("CTSH", "Cognizant Technology Solutions Corporation");
        stocksmap.put("AFT", "Apollo Senior Floating Rate Fund Inc.");
        stocksmap.put("LINC", "Lincoln Educational Services Corporation");
        stocksmap.put("ALL", "Allstate Corporation (The)");
        stocksmap.put("SHSP", "SharpSpring, Inc.");
        stocksmap.put("BLRX", "BioLineRx Ltd.");
        stocksmap.put("PEP", "Pepsico, Inc.");
        stocksmap.put("XOXO", "XO Group, Inc.");
        stocksmap.put("SOL", "Renesola Ltd.");
        stocksmap.put("USLB", "PowerShares Russell 1000 Low Beta Equal Weight Portfolio");
        stocksmap.put("LOXO", "Loxo Oncology, Inc.");
        stocksmap.put("AUBN", "Auburn National Bancorporation, Inc.");
        stocksmap.put("GHDX", "Genomic Health, Inc.");
        stocksmap.put("GVA", "Granite Construction Incorporated");
        stocksmap.put("JAX", "J. Alexander's Holdings, Inc.");
        stocksmap.put("FUSB", "First US Bancshares, Inc.");
        stocksmap.put("DTRM", "Determine, Inc. ");
        stocksmap.put("BVXVW", "BiondVax Pharmaceuticals Ltd.");
        stocksmap.put("QQQX", "Nuveen NASDAQ 100 Dynamic Overwrite Fund");
        stocksmap.put("TTNP", "Titan Pharmaceuticals, Inc.");
        stocksmap.put("VIAV", "Viavi Solutions Inc.");
        stocksmap.put("VREX", "Varex Imaging Corporation");
        stocksmap.put("GATX", "GATX Corporation");
        stocksmap.put("BAM", "Brookfield Asset Management Inc");
        stocksmap.put("BDC", "Belden Inc");
        stocksmap.put("USAC", "USA Compression Partners, LP");
        stocksmap.put("ESG", "FlexShares STOXX US ESG Impact Index Fund");
        stocksmap.put("DBD", "Diebold Nixdorf Incorporated");
        stocksmap.put("CDI", "CDI Corporation");
        stocksmap.put("FJP", "First Trust Japan AlphaDEX Fund");
        stocksmap.put("BOBE", "Bob Evans Farms, Inc.");
        stocksmap.put("LPTH", "LightPath Technologies, Inc.");
        stocksmap.put("KBSF", "KBS Fashion Group Limited");
        stocksmap.put("WHLRW", "Wheeler Real Estate Investment Trust, Inc.");
        stocksmap.put("SLM", "SLM Corporation");
        stocksmap.put("BIOP", "Bioptix, Inc");
        stocksmap.put("GPT", "Gramercy Property Trust");
        stocksmap.put("INST", "Instructure, Inc.");
        stocksmap.put("CSTM", "Constellium N.V.");
        stocksmap.put("OMEX", "Odyssey Marine Exploration, Inc.");
        stocksmap.put("O", "Realty Income Corporation");
        stocksmap.put("XHR", "Xenia Hotels & Resorts, Inc.");
        stocksmap.put("PTR", "PetroChina Company Limited");
        stocksmap.put("AHL", "Aspen Insurance Holdings Limited");
        stocksmap.put("BG", "Bunge Limited");
        stocksmap.put("RFIL", "RF Industries, Ltd.");
        stocksmap.put("CRIS", "Curis, Inc.");
        stocksmap.put("BJRI", "BJ's Restaurants, Inc.");
        stocksmap.put("REN", "Resolute Energy Corporation");
        stocksmap.put("PCK", "Pimco California Municipal Income Fund II");
        stocksmap.put("BLUE", "bluebird bio, Inc.");
        stocksmap.put("PRAA", "PRA Group, Inc.");
        stocksmap.put("KALU", "Kaiser Aluminum Corporation");
        stocksmap.put("MSL", "MidSouth Bancorp");
        stocksmap.put("PLBC", "Plumas Bancorp");
        stocksmap.put("KTOS", "Kratos Defense & Security Solutions, Inc.");
        stocksmap.put("HII", "Huntington Ingalls Industries, Inc.");
        stocksmap.put("REXR^A", "Rexford Industrial Realty, Inc.");
        stocksmap.put("BKS", "Barnes & Noble, Inc.");
        stocksmap.put("CBD", "Companhia Brasileira de Distribuicao");
        stocksmap.put("SAB", "Saratoga Investment Corp");
        stocksmap.put("WRI", "Weingarten Realty Investors");
        stocksmap.put("MMM", "3M Company");
        stocksmap.put("IDSY", "I.D. Systems, Inc.");
        stocksmap.put("INWK", "InnerWorkings, Inc.");
        stocksmap.put("SSW^D", "Seaspan Corporation");
        stocksmap.put("KT", "KT Corporation");
        stocksmap.put("FDP", "Fresh Del Monte Produce, Inc.");
        stocksmap.put("AFSI^C", "AmTrust Financial Services, Inc.");
        stocksmap.put("MLAB", "Mesa Laboratories, Inc.");
        stocksmap.put("LAD", "Lithia Motors, Inc.");
        stocksmap.put("MELI", "MercadoLibre, Inc.");
        stocksmap.put("MOH", "Molina Healthcare Inc");
        stocksmap.put("WSBC", "WesBanco, Inc.");
        stocksmap.put("CLRBZ", "Cellectar Biosciences, Inc.");
        stocksmap.put("LEE", "Lee Enterprises, Incorporated");
    }

    @RequestMapping(value = "getstocks", method = RequestMethod.POST, produces = {"application/json"})
    public List<Double> getPrice(@RequestBody getdata req){

            for(String s: tickers){
                if(req.getTickerSymbol() == null){
                    req.setTickerSymbol("no ticker symbol");
                    return req.getValues();
                }

                else if(req.getTickerSymbol().equals(s)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) {
                Random rand = new Random();
                Date now = new Date();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("Sunday",0);
                map.put("Monday",1);
                map.put("Tuesday",2);
                map.put("Wednesday",3);
                map.put("Thursday",4);
                map.put("Friday",5);
                map.put("Saturday",0);
                String day;
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                day = sdf.format(now);
                LocalTime time = LocalTime.now();
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                List<Double> l = new ArrayList<>();
                String range = req.getRange();
                switch(range){
                    case "current": {
                        l.clear();
                        double d = rand.nextDouble();
                        int p = rand.nextInt(200);
                        double r = p + d;
                        l.add(r);
                        return l;
                        //req.setValues(l);

                        //return req;
                    }
                    case "week": {
                        l.clear();
                        int i = map.get(day);
                        for(int j=0; j<4; j++) {
                            double d = rand.nextDouble();
                            int p = rand.nextInt(200);
                            double r = p + d;
                            l.add(r);
                        }
                        return l;
                    }
                    case "pastweek": {
                        l.clear();
                        for(int j=0; j<5; j++){
                            double d = rand.nextDouble();
                            int p = rand.nextInt(200);
                            double r = p + d;
                            l.add(r);
                        }
                        return l;
                    }
                    case "month": {
                        l.clear();
                        for(int j=0; j<24; j++){
                            double d = rand.nextDouble();
                            int p = rand.nextInt(200);
                            double r = p + d;
                            l.add(r);
                        }
                        return l;
                    }
                    case "year": {
                        l.clear();
                        for(int j=0; j<236; j++){
                            double d = rand.nextDouble();
                            int p = rand.nextInt(200);
                            double r = p + d;
                            l.add(r);
                        }
                        return l;
                    }
                    case "5years": {
                        l.clear();
                        for(int j=0; j<1279; j++){
                            double d = rand.nextDouble();
                            int p = rand.nextInt(200);
                            double r = p + d;
                            l.add(r);
                        }
                        return l;
                    }
                }
                return l;
            }
            else
                return req.getValues();
    }

    @RequestMapping(value = "getAllStocks", method = RequestMethod.GET, produces = {"application/json"})
    public List<stocks> getAllStocks(){
        Random rand = new Random();


        List<stocks> allStocks=new ArrayList<stocks>();
            for(String s: tickers) {
                stocks newstock = new stocks();
                newstock.setTickersymbol(s);
                newstock.setStockname(stocksmap.get(s));
                double d = rand.nextDouble();
                int p = rand.nextInt(200);
                double r = p + d;
                newstock.setCurrentprice(r);
                allStocks.add(newstock);
            }
            return allStocks;
        }

    }
