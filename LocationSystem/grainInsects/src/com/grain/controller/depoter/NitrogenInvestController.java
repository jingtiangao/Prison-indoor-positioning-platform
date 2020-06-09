package com.grain.controller.depoter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/depoter/nitrogenInvest")
public class NitrogenInvestController {

	@RequestMapping("/nitrogen_invest_list")
	public String nitrogenInvest() {
		return "depoter/dataReport/nitrogenInvest/nitrogen_invest";
	}
	
	@RequestMapping("/addNitrogenInvest")
	public String addBinInvest() {
		return "depoter/dataReport/nitrogenInvest/addNitrogenInvest";
	}
	
	@RequestMapping("/editBinInvest")
	public String editBinInvest() {
		return "depoter/dataReport/depotdivideinvest/editGrainBinInvest";
	}
	
	
	
	
	@RequestMapping("/depot_HP3_list")
	public String depotHP3Invest() {
		return "depoter/dataReport/depotHP3Invest/depot_HP3_list";
	}
	
	@RequestMapping("/addDepotHP3InvestEntrance")
	public String addDepotHP3Invest() {
		return "depoter/dataReport/depotHP3Invest/addDepotHP3";
	}
	
	@RequestMapping("/editDepotHP3Entrance")
	public String editDepotHP3Invest() {
		return "depoter/dataReport/depotHP3Invest/editDepotHP3";
	}
	
	
	
	
	@RequestMapping("/bin_HP3_list")
	public String binHP3Invest() {
		return "depoter/dataReport/binHP3Invest/bin_HP3_list";
	}
	
	@RequestMapping("/addBinHP3InvestEntrance")
	public String addBinHP3Invest() {
		return "depoter/dataReport/binHP3Invest/addBinHP3";
	}
	
	@RequestMapping("/editBinHP3Entrance")
	public String editBinHP3Invest() {
		return "depoter/dataReport/binHP3Invest/editBinHP3";
	}
	
	
}
