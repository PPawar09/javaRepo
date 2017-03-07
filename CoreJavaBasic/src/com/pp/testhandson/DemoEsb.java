/*package com.pp.testhandson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.ws.util.UUID;
import com.kahg.ess.vinservice.vin.ACORDDocument;
import com.kahg.ess.vinservice.vin.ACORDType;
import com.kahg.ess.vinservice.vin.ClientAppType;
import com.kahg.ess.vinservice.vin.CollisionSymbolDataDocument.CollisionSymbolData;
import com.kahg.ess.vinservice.vin.ComprehensiveOTCSymbolDataDocument.ComprehensiveOTCSymbolData;
import com.kahg.ess.vinservice.vin.DateTime;
import com.kahg.ess.vinservice.vin.GetSeriesDetailResultDocument.GetSeriesDetailResult;
import com.kahg.ess.vinservice.vin.GetSeriesDetailRqDocument.GetSeriesDetailRq;
import com.kahg.ess.vinservice.vin.GetSeriesResultDocument.GetSeriesResult;
import com.kahg.ess.vinservice.vin.GetSeriesRqDocument.GetSeriesRq;
import com.kahg.ess.vinservice.vin.GetSymbolRqDocument.GetSymbolRq;
import com.kahg.ess.vinservice.vin.INVINDocument.INVIN;
import com.kahg.ess.vinservice.vin.OpenEnum;
import com.kahg.ess.vinservice.vin.SignonRqType;
import com.kahg.ess.vinservice.vin.VINSvcRqType;
import com.kahg.ess.vinservice.vin.VSRSymbolDataType;
import com.kahg.ess.vinservice.vin.ValidateVINRqDocument;
import com.kahg.ess.vinservice.vin.VehicleType;
import com.kahg.ess.vinservice.vin.VehicleType.Enum;
import com.kahg.jcf.errors.ApplicationException;
import com.kahg.jcf.errors.SystemException;
import com.kahg.jcf.utilities.context.ApplicationContext;
import com.kahg.rpw.util.CommonConstants;
import com.kahg.rpw.util.ExceptionMsgUtil;
import com.kahg.rpw.vo.ModelVo;
import com.kahg.rpw.vo.QuoteVO;
import com.kahg.rpw.vo.VINDetailsVO;
import com.kahg.rpw.vo.VINInformationVO;
import com.kahg.vin.client.VINSvcClient;
import com.kahg.vin.errors.VINException;

public class DemoEsb {
	
	private static final Logger LOG = Logger.getLogger(DemoEsb.class.getName());
	public static final String SERVICE_FACTORY_CLASS = "ESB.Topic.FACTORY_CLASS";
	private static String SONIC_INITIAL_CONTEXT_FACTORY = "com.sonicsw.jndi.mfcontext.MFContextFactory";
	private static String SONIC_DOMAIN = "KAHDomain";
	private static String SONIC_SECURITY_PRINCIPAL = "USGClient";
	private static String SONIC_SECURITY_CREDENTIALS = "USGClient"; 
	private static String SONIC_IDLE_TIMEOUT = "6000";
	private static javax.jms.ConnectionFactory factory = null;
	private javax.jms.Session sendSession = null;
	private javax.jms.Session receiveSession = null;
	private javax.jms.MessageProducer sender = null;
	private javax.jms.MessageConsumer receiver = null;
	private long timeOut = 30000;
	private static javax.jms.Connection connection = null;
	private static javax.jms.Session session = null;
	private static String SONIC_PROVIDER_URL = null;
	private transient String symbol = null;

	public static void main(String[] args) {
		
		try {
			DemoEsb esb = new DemoEsb();
			//Domain Manager Port are not working from JMS Client.
			// tcp://kahobtesbb93.kah.unitrininc.com:2906
			//Message Broker port is working.
			//SONIC_PROVIDER_URL = "tcp://kahobtesbb93.kah.unitrininc.com:2616"; // DEV
			//SONIC_PROVIDER_URL = "tcp://kahobtesbb93.kah.unitrininc.com:2916"; // DEV1
			//SONIC_PROVIDER_URL = "tcp://kahobtesbb91.kah.unitrininc.com:2516"; // INT
			SONIC_PROVIDER_URL = "tcp://kahobtesbb91.kah.unitrininc.com:2616"; // UAT
			//SONIC_PROVIDER_URL = "tcp://kahobtesbb41.kah.unitrininc.com:2516"; // PROD
					
			factory = new progress.message.jclient.ConnectionFactory(SONIC_PROVIDER_URL, SONIC_SECURITY_PRINCIPAL,SONIC_SECURITY_CREDENTIALS); //UAT
			//factory = new progress.message.jclient.ConnectionFactory(SONIC_PROVIDER_URL,SONIC_SECURITY_PRINCIPAL,SONIC_SECURITY_CREDENTIALS);
			//connection = factory.createConnection();
			//session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			//final Topic sendTopic = session.createTopic("com.kahg.ess.vinsvc.main.entry");
			//esb.getVinSeries(factory);
			//	,"25000","1994"
						QuoteVO vo = new QuoteVO();
			vo.setQuoteNumberKey(54923847);

			 String[] strArray = new String[]
					 {"JTMZFREV6FJ044098", 
					 "WDDUG8CBXFA083485"};
			 
			List vinLst = Arrays.asList(strArray);
			for(int i=0; i < vinLst.size();i++){
				String vin = (String)vinLst.get(i);
				VINDetailsVO vo = esb.getVINDetails(54923847 ,vin,"25000","1994",factory);
				System.out.println("VIN# "+vin+"****** LP : " + vo.getLiabSymbol() + " **** MP : " + vo.getMedpaySymbol());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getVinSeries(ConnectionFactory cf) throws SystemException,Exception{
		ACORDDocument response = makeSeriesRequest(2015,"AUDI",cf);
		//ACORDDocument response = makeSeriesRequest(2015,"AUD",cf); DEV1
	}
	
	private static ACORDDocument makeSeriesRequest(int year,String vehicleMake,ConnectionFactory cf) throws XmlException, IOException {
		ACORDDocument response = null;
		ACORDDocument acordDoc = ACORDDocument.Factory.newInstance();
		createSignOn(acordDoc);
		
		//build getSeries Request
		GetSeriesRq getSeriesRq = acordDoc.getACORD().getVINSvcRq().addNewGetSeriesRq();
		getSeriesRq.setInputMake(vehicleMake);
		String vehicleYear = Integer.toString(year);
		getSeriesRq.setInputYear(vehicleYear);
		getSeriesRq.setVehicleType(VehicleType.V);
		
		try {
			VINSvcClient client = new VINSvcClient(cf);
			client.setTimeOut(6000);
			String messageText = acordDoc.xmlText();
			LOG.info("Get Series Request : " + messageText);
			long before = System.currentTimeMillis();
			response = client.vin(acordDoc, "com.kahg.ess.vinsvc.main.entry");
			long after = System.currentTimeMillis();
			long x = after-before;
			
			LOG.info("Get Series Time Elapsed " + x);
			LOG.info("Get Series Response : " + response.xmlText());
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (VINException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	private static ACORDDocument createSignOn(ACORDDocument acordDoc) {
		ACORDType acord = acordDoc.addNewACORD();
		SignonRqType signonRqType = acord.addNewSignonRq();
		signonRqType.setClientDt("2008-04-01");
		signonRqType.setCustLangPref("ENG");
		ClientAppType clientAppType = signonRqType.addNewClientApp();
		clientAppType.setOrg("Unitrin");
		clientAppType.setName("RPW6.1");
		clientAppType.setVersion("1.0");
		VINSvcRqType vinSvcRq = acordDoc.getACORD().addNewVINSvcRq();
		vinSvcRq.setRqUID((new UUID()).toString()); // This is required
		DateTime date = vinSvcRq.addNewTransactionRequestDt();
		date.setStringValue("2008-02-21T11:19:35.58-6:00");
		OpenEnum curCd = vinSvcRq.addNewCurCd();
		curCd.setStringValue("USD");
		return acordDoc;
	}
	
	public VINDetailsVO getVINDetails(int quoteNumber, String VIN, String costNew, String year,ConnectionFactory cf)
			throws SystemException, ApplicationException {
		
		long after = -1;
		String cost = (costNew == null) ? " " : costNew;
		ACORDDocument response = null;
		VINDetailsVO vinDetailsVO = new VINDetailsVO();
		setDefaultVINDetails(vinDetailsVO);
		long before = System.currentTimeMillis();
		response = searchByVIN(quoteNumber, VIN, "037", "MO", "2222", year,cf);
		after = System.currentTimeMillis();
		///LOG.log(Level.INFO, "VIN Lookup Response : " + response.xmlText());
		///LOG.log(Level.WARNING, "ESBServiceCall=getVINDetails|elapsedTime=" + (after - before));
		
		setSymbolsForUnknownVIN(VIN, year, response, vinDetailsVO);
		String vehicleType = determineVehicleType(response);
		int returnCode = determineReturnCode(response, vehicleType);
		if (returnCode < 4) {
			setYearMakeModel(response, vinDetailsVO);
			setPassiveRestraints(response, vinDetailsVO);
			setAntiLockBrakes(response, vinDetailsVO);
			setBodyType(response, vinDetailsVO);
			if (vinDetailsVO.getYear() <= 2010) {
				setAttributesBefore2011ModelYear(response, vinDetailsVO);
			} else {
				setAttributesAfter2010ModelYear(response, vinDetailsVO);
			}
			setSymbol(vinDetailsVO);
			setVINMask(response, vinDetailsVO);
			setCorrectedVIN(response, vinDetailsVO, returnCode);
			setBaseCostListExt(response, vinDetailsVO);
			setLiabilitySymbol(response, vinDetailsVO,null);
			setMedpaySymbol(response, vinDetailsVO,null);
		} else {
			setLiabilitySymbol(response, vinDetailsVO,null);
			setMedpaySymbol(response, vinDetailsVO,null);
		}
		return vinDetailsVO;
	}

	*//**
	 * Get VIN Mask
	 * 
	 * @param quoteNumber
	 * @param vehicleYear
	 * @param vehicleMake
	 * @param vehicleModel
	 * @return
	 * @throws SystemException
	 * @throws ApplicationException
	 *//*
	public List<VINInformationVO> getVINMask(QuoteVO quoteNumber, int vehicleYear, String vehicleMake,
			String vehicleModel) throws SystemException, ApplicationException {
		List<VINInformationVO> results = new ArrayList<VINInformationVO>(10);

		if (vehicleModel.equals("NONE")) {
			return results;
		}
		try {
			ACORDDocument response = makeSeriesDetailRequest(vehicleYear, vehicleMake, vehicleModel);
			results = createSeriesDetailArray(quoteNumber, response);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, ExceptionMsgUtil.getMessage("VINException caught in {0}"), e);
		} 
		return results;
	}

	*//**
	 * Search by VIN and return AcordDocument repsonse.
	 * 
	 * @param quoteNumber
	 * @param vin
	 * @param company
	 * @param state
	 * @param costNew
	 * @param year
	 * @return
	 *//*
	private ACORDDocument searchByVIN(Integer quoteNumber, String vin, String company, String state, String costNew,
			String year,ConnectionFactory cf) {
		
		long after = -1;
		String sVinOldNumber = vin.trim();
		ACORDDocument response = null;
		try {
			VINSvcClient client = new VINSvcClient(cf);
			System.out.println("************Sending Request*************");
			client.setTimeOut(3000);
			long before = System.currentTimeMillis();
			response = client.vin(makeVINRequest(sVinOldNumber, company, state, costNew, year),
					"com.kahg.ess.vinsvc.main.entry");
			after = System.currentTimeMillis();
			
			LOG.log(Level.WARNING, "ESBServiceCall=searchByVIN|elapsedTime=" + (after - before));
		} catch (JMSException e) {
			LOG.log(Level.SEVERE, ExceptionMsgUtil.getMessage(ExceptionMsgUtil.JMS_EXCEPTION), e);
		} catch (VINException e) {
			LOG.log(Level.SEVERE, ExceptionMsgUtil.getMessage("VINException caught in {0}"), e);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, ExceptionMsgUtil.getMessage("VINException caught in {0}"), e);
		}
		return response;
	}

	*//**
	 * Set VINDetails anti-lock brakes from the VIN service response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setAntiLockBrakes(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String derivedABS = response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
				.getDerivedABS();
		if (derivedABS != null) {
			vinDetailsVO.setAntiLockBrakes(derivedABS);
		}
	}

	*//**
	 * Set response attributes for vehicles with a model year after 2010. These
	 * are the new symbols.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setAttributesAfter2010ModelYear(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		setSymbolAndHighPerfCode(response, vinDetailsVO);
		setCompSymbolAndCompHighPerfCode(response, vinDetailsVO);
		setCollSymbolAndCollHighPerfCode(response, vinDetailsVO);
	}

	*//**
	 * Set response attributes for vehicles with a model year prior to 2011.
	 * These are old symbols.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setAttributesBefore2011ModelYear(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		this.symbol = response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getSymbolData().getISOData()
				.getVSRSymbolData().getVSRSymbol();
		vinDetailsVO.setHighPerformanceCode(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN()
				.getSymbolData().getISOData().getVSRSymbolData().getVSRPerformanceInd());
	}

	*//**
	 * set VINDetails BaseCostListExt from the VIN service response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setBaseCostListExt(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String costNew = response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getBaseListPriceExpanded();
		if (!(costNew == null)) {
			if (StringUtils.equals(costNew, "0000000")) {
				vinDetailsVO.setBaseListCostExt("");
			} else {
				costNew = removeLeadingZeros(costNew);
				vinDetailsVO.setActualBaseListCostExt(costNew);//Setting actual MSRP from Service to store in LPMP06
				if ((costNew.trim().length() > 0) && (Integer.parseInt(costNew) >= 100000)) {
					vinDetailsVO.setBaseListCostExt("99999");
				} else {
					vinDetailsVO.setBaseListCostExt(costNew);
				}
			}
		}
	}

	*//**
	 * Set VINDetails body type from the response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setBodyType(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String bodyType = response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
				.getDerivedBodyType();
		if (bodyType != null) {
			vinDetailsVO.setBodyType(bodyType);
		}
	}

	*//**
	 * Set VINDetails collision symbol and collision high performance code for
	 * model years after 2010. String collSymbol is initially null. the method
	 * attempts to populate the real value from the VIN service response.
	 * However, if it is unable to do so or the value returned was null or
	 * blank, then a default "00" is assigned.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setCollSymbolAndCollHighPerfCode(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String collSymbol = null;
		CollisionSymbolData collisionSymbolData = response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getCollisionSymbolData();
		if (collisionSymbolData != null) {
			collSymbol = collisionSymbolData.getCollisionSymbolCd();
			vinDetailsVO.setCollHighPerformanceCode(collisionSymbolData.getCollisionPerformanceInd());
		}
		if ((StringUtils.isBlank(collSymbol)) || (collSymbol == null)) {
			collSymbol = "00";
		}
		vinDetailsVO.setCollSymbol(collSymbol);
	}

	*//**
	 * Set VINDetails comp symbol and comp high performance code for model years
	 * after 2010. String compSymbol is initially null. the method attempts to
	 * populate the real value from the VIN service response. However, if it is
	 * unable to do so or the value returned was null or blank, then a default
	 * "00" is assigned.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setCompSymbolAndCompHighPerfCode(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String compSymbol = null;
		ComprehensiveOTCSymbolData comprehensiveOTCSymbolData = response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getComprehensiveOTCSymbolData();
		if (comprehensiveOTCSymbolData != null) {
			compSymbol = comprehensiveOTCSymbolData.getComprehensiveOTCSymbolCd();
			vinDetailsVO.setCompHighPerformanceCode(comprehensiveOTCSymbolData.getComprehensiveOTCPerformanceInd());
		}
		if ((StringUtils.isBlank(compSymbol)) || (compSymbol == null)) {
			compSymbol = "00";
		}
		vinDetailsVO.setCompSymbol(compSymbol);
	}

	*//**
	 * Set VINDetails CorrectedVIN from the VIN service response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 * @param retCode
	 *//*
	private void setCorrectedVIN(ACORDDocument response, VINDetailsVO vinDetailsVO, int retCode) {
		if ((retCode == 2) || (retCode == 3)) {
			String correctedVIN = response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getCorrectedVIN();
			if (correctedVIN != null) {
				vinDetailsVO.setCorrectedVIN(correctedVIN);
			}
		}
	}

	*//**
	 * Set the default VINDetails fields.
	 * 
	 * @param vinDetailsVO
	 *//*
	private void setDefaultVINDetails(VINDetailsVO vinDetailsVO) {
		vinDetailsVO.setAntiLockBrakes("");
		vinDetailsVO.setBaseListCostExt("");
		vinDetailsVO.setBodyType("");
		vinDetailsVO.setCollSymbol("00");
		vinDetailsVO.setCompSymbol("00");
		vinDetailsVO.setCorrectedVIN("");
		vinDetailsVO.setLiabSymbol("");
		vinDetailsVO.setMake("");
		vinDetailsVO.setMedpaySymbol("");
		vinDetailsVO.setModel("");
		vinDetailsVO.setPassiveRestraints("");
		vinDetailsVO.setSymbol("");
		vinDetailsVO.setVinMask("");
	}

	*//**
	 * Set VINDetails passive constraints from the VIN service response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setPassiveRestraints(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String derivedConstraint = response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
				.getDerivedRestraint();
		if (derivedConstraint != null) {
			vinDetailsVO.setPassiveRestraints(derivedConstraint);
		}
	}

	*//**
	 * Set the VINDetails Symbol.
	 * 
	 * @param vinDetailsVO
	 * @param symbol
	 *//*
	private void setSymbol(VINDetailsVO vinDetailsVO) {
		if (this.symbol == null || StringUtils.isBlank(this.symbol)) {
			this.symbol = "00";
		}
		vinDetailsVO.setSymbol(this.symbol);
	}

	*//**
	 * Set VINDetails symbol and high performance indicator for model years
	 * after 2010.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setSymbolAndHighPerfCode(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		VSRSymbolDataType vsrSymbolDataType = response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
				.getVSRSymbolData();
		if (vsrSymbolDataType != null) {
			this.symbol = vsrSymbolDataType.getVSRSymbol();
			vinDetailsVO.setHighPerformanceCode(vsrSymbolDataType.getVSRPerformanceInd());
		} else {
			this.symbol = "00";
			vinDetailsVO.setHighPerformanceCode("0");
		}
	}

	*//**
	 * Sets symbols on VINDetail for unknown VIN from the VIN service response.
	 * 
	 * @param VIN
	 * @param year
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setSymbolsForUnknownVIN(String VIN, String year, ACORDDocument response, VINDetailsVO vinDetailsVO) {
		if (StringUtils.equals(VIN, "?????")) {
			if (Integer.parseInt(year) <= 2010) {
				vinDetailsVO.setSymbol(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getSymbolData()
						.getISOData().getVSRSymbolData().getVSRSymbol());
			} else {
				if (response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
						.getVSRSymbolData() != null) {
					vinDetailsVO.setSymbol(response.getACORD().getVINSvcRs().getValidateVINRs()
							.getKemperDerivedAttributes().getVSRSymbolData().getVSRSymbol());
				} else {
					vinDetailsVO.setSymbol("00");
				}
				if (response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
						.getComprehensiveOTCSymbolData() != null) {
					vinDetailsVO
							.setCompSymbol(response.getACORD().getVINSvcRs().getValidateVINRs()
									.getKemperDerivedAttributes().getComprehensiveOTCSymbolData()
									.getComprehensiveOTCSymbolCd());
				}
				if (response.getACORD().getVINSvcRs().getValidateVINRs().getKemperDerivedAttributes()
						.getCollisionSymbolData() != null) {
					vinDetailsVO.setCollSymbol(response.getACORD().getVINSvcRs().getValidateVINRs()
							.getKemperDerivedAttributes().getCollisionSymbolData().getCollisionSymbolCd());
				}
			}
		}
	}

	*//**
	 * Set VINDetails VINMask from the VIN service response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setVINMask(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		String inputVIN = response.getACORD().getVINSvcRs().getValidateVINRs().getINVIN().getInputVIN();
		if (inputVIN != null) {
			vinDetailsVO.setVinMask(inputVIN);
		}
	}
	
	*//**
	 * Set VINDetails liabSymbol from the VIN service response.  It is a concatenation of
	 * liability performance indicator (1 char) and liability symbol code (2 chars).
	 * 
	 * @param response
	 * @param vinDetailsVO
	 * @param quoteNumber 
	 *//*
	private void setLiabilitySymbol(ACORDDocument response, VINDetailsVO vinDetailsVO, QuoteVO quoteVo) {
		StringBuffer liabilitySymbol = new StringBuffer(3);
		liabilitySymbol.append(StringUtils.trimToEmpty(response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getLiabilitySymbolData().getLiabilityPerformanceInd()));
		liabilitySymbol.append(StringUtils.trimToEmpty(response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getLiabilitySymbolData().getLiabilitySymbolCd()));
		if (liabilitySymbol.length() == 3) {
			vinDetailsVO.setLiabSymbol(liabilitySymbol.toString());
		}
	}
	
	*//**
	 * Set VINDetails medpaySymbol from the VIN service response.  It is a concatenation of
	 * med pay performance indicator (1 char) and med pay symbol code (2 chars).
	 * 
	 * @param response
	 * @param vinDetailsVO
	 * @param quoteNumber 
	 *//*
	private void setMedpaySymbol(ACORDDocument response, VINDetailsVO vinDetailsVO, QuoteVO quoteVo) {
		StringBuffer medpaySymbol = new StringBuffer(3);
		medpaySymbol.append(StringUtils.trimToEmpty(response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getMedicalSymbolData().getMedicalPerformanceInd()));
		medpaySymbol.append(StringUtils.trimToEmpty(response.getACORD().getVINSvcRs().getValidateVINRs()
				.getKemperDerivedAttributes().getMedicalSymbolData().getMedicalSymbolCd()));
		if (medpaySymbol.length() == 3) {
			vinDetailsVO.setMedpaySymbol(medpaySymbol.toString());
		}
	}

	*//**
	 * Set the year make and model into VINDetails from the VIN service
	 * response.
	 * 
	 * @param response
	 * @param vinDetailsVO
	 *//*
	private void setYearMakeModel(ACORDDocument response, VINDetailsVO vinDetailsVO) {
		vinDetailsVO.setYear(Integer.parseInt(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN()
				.getOutputYearModel()));
		vinDetailsVO.setMake(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getNCICMake());
		vinDetailsVO.setModel(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getSeriesName());
	}


	private static String removeLeadingZeros(String str) {
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int index = 0;
		for (; index < str.length(); index++) {
			if (chars[index] != '0') {
				break;
			}
		}
		return (index == 0) ? str : str.substring(index);
	}
	
	private static ACORDDocument makeVINRequest(String sVinNumber, String co, String st, String costNew, String year)
			throws XmlException, IOException {
		ACORDDocument acordDoc = ACORDDocument.Factory.newInstance();
		createSignOn(acordDoc);
		ValidateVINRqDocument.ValidateVINRq validateVINRq = acordDoc.getACORD().getVINSvcRq().addNewValidateVINRq();
		INVIN invin = validateVINRq.addNewINVIN();
		invin.setInputVIN(sVinNumber);
		if (!StringUtils.equalsIgnoreCase(year, "????")) {
			invin.setInputYear(year);
		}
		validateVINRq.setCompanyCd(co);
		if (StringUtils.isNotBlank(costNew)) {
			validateVINRq.setCostNew(costNew);
		}
		OpenEnum state = validateVINRq.addNewStateProvCd();
		state.setStringValue(st);
		validateVINRq.setStateProvCd(state);
		String messageText = acordDoc.xmlText();
		//LOG.log(Level.INFO, "VIN Lookup Request : " + messageText);
		return acordDoc;
	}
	
	private int determineReturnCode(ACORDDocument response, String vehicleType) {
		int returnCode;
		returnCode = Integer.parseInt(response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getReturnCode());
		if ((!StringUtils.equalsIgnoreCase(vehicleType, "T")) && (!StringUtils.equalsIgnoreCase(vehicleType, "P"))) {
			returnCode = 5;
		}
		return returnCode;
	}

	*//**
	 * Determine the vehicle type from the response and return it as a String.
	 * 
	 * @param response
	 * @return
	 *//*
	private String determineVehicleType(ACORDDocument response) {
		String vehicleType = " ";
		Enum vehicleTypeEnum = response.getACORD().getVINSvcRs().getValidateVINRs().getOUTVIN().getVehicleType();
		if (vehicleTypeEnum != null) {
			vehicleType = vehicleTypeEnum.toString();
		}
		return vehicleType;
	}
	


}
*/