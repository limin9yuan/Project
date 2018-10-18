package org.module.fc.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.HouseService;

public class HouseImpl extends BaseServiceImpl implements HouseService{

	
		/**
		 * 添加房间
		 * @param pDto
		 * @return
		 */
		public Dto insertHouse(Dto pDto) {
			Dto outDto = new BaseDto();	
			Integer countInteger = (Integer) g4Dao.queryForObject("isHouseExist", pDto);
			if (countInteger.intValue() > 0) {
				outDto.put("success", new Boolean(false));
				outDto.put("msg", "该用户编号系统中已经存在，不能添加！");
				return outDto;
			}
			try{
				g4Dao.insert("insertHouse", pDto);
				if(((String)pDto.get("owner_name"))!=null){
					pDto.put("owner_name",((String)pDto.get("owner_name")).trim());
				}
				g4Dao.insert("insertOwner", pDto);

				if(
					(pDto.get("use_type")!=null && !"".equals(pDto.getAsString("use_type").trim()))
					|| (pDto.get("standard_id")!=null && !"".equals(pDto.getAsString("standard_id").trim()))
					|| (pDto.get("charge_area")!=null && !"".equals(pDto.getAsString("charge_area").trim()))){
					
					if(pDto.get("charge_area")==null || "".equals(pDto.get("charge_area"))){
						pDto.put("charge_area",0);
					}
					g4Dao.insert("insertArea", pDto);
				}
				
				g4Dao.update("updateBuHouseCount", pDto);		
				g4Dao.update("updateCmHouseCount", pDto);
	
				outDto.put("success", new Boolean(true));
			}catch (Exception e){
				outDto.put("success", new Boolean(false));
				outDto.put("error",e.getMessage());
			}
			
			outDto.put("msg", "用户添加成功!");
			return outDto;
		}
		/**
		 * 修改房间
		 * @param pDto
		 * @return
		 */	
		public Dto updateHouse(Dto pDto) {
			Dto outDto = new BaseDto();
			g4Dao.update("updateHouse", pDto);
			g4Dao.update("updateOwner", pDto);
			Integer countInteger = (Integer) g4Dao.queryForObject("haveArea1", pDto);
			if (countInteger.intValue() > 0) {
				g4Dao.update("updateArea", pDto);
			}else{
				if(!"".equals(pDto.getAsString("standard_id")) && !"".equals(pDto.getAsString("charge_area"))){
					pDto.put("area_id", "0");
					g4Dao.update("insertAreas", pDto);
				}				
			}
			
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "用户修改成功!");
			return outDto;
		}
		/**
		 * 删除房间
		 * @param pDto
		 * @return
		 */	
		public Dto deleteHouse(Dto pDto) {
			Dto outDto = new BaseDto();
			
			Integer countInteger = (Integer) g4Dao.queryForObject("haveNotcharge", pDto);
			System.out.println(g4Dao.queryForObject("haveNotcharge", pDto));
			if (countInteger.intValue() > 0) {
				//outDto.put("success", new Boolean(false));
				outDto.put("msg", (String)pDto.get("house_code")+(String)pDto.get("owner_name")+",该用户有应收,不能删除!请先删除该用户应收!");
				return outDto;
			}

			g4Dao.delete("deleteHouse", pDto);
			g4Dao.delete("deleteOwner", pDto);
			g4Dao.delete("deleteArea", pDto);
			g4Dao.update("updateBuHouseCount", pDto);		
			g4Dao.update("updateCmHouseCount", pDto);	
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "用户删除成功!");
			return outDto;
		}
		
		/**
		 * 批量删除房间
		 * @param pDto
		 * @return
		 */	
		public Dto batchDeleteHouse(Dto pDto) {
			Dto outDto = new BaseDto();
			
			Integer countInteger = (Integer) g4Dao.queryForObject("haveNotcharge2", pDto);
			System.out.println(g4Dao.queryForObject("haveNotcharge2", pDto));
			Integer batchDeleteHouseCount = (Integer) g4Dao.queryForObject("batchDeleteHouseCount", pDto);
			String str=Integer.toString(countInteger);
			String HouseCount=Integer.toString(batchDeleteHouseCount);
			/*if (countInteger.intValue() > 0) {
				//outDto.put("success", new Boolean(true));
				//String str=Integer.toString(countInteger);
				System.out.println(str);
				System.out.println((String)str+"个用户有欠费,不能删除!");
				outDto.put("msg", ",该用户有欠费,不能删除!");
				outDto.put("msg", (String)str+"个用户有欠费,不能删除!");
				return outDto;
			}*/

			g4Dao.delete("batchDeleteHouse", pDto);
			g4Dao.delete("batchDeleteOwner", pDto);
			g4Dao.delete("batchDeleteArea", pDto);
			g4Dao.update("updateBuHouseCount", pDto);		
			g4Dao.update("updateCmHouseCount", pDto);	
			outDto.put("success", new Boolean(true));
			outDto.put("msg", (String)HouseCount+"个用户删除成功!"+(String)str+"个用户有应收,不能删除!");
			return outDto;
		}
		/**
		 * 添加房间面积2(导入房间)
		 * @param pDto
		 * @return
		 */
		public Dto insertArea(Dto pDto) {
			Dto outDto = new BaseDto();	
			try{
				g4Dao.insert("insertArea", pDto);
				g4Dao.update("updateBuHouseCount", pDto);		
				g4Dao.update("updateCmHouseCount", pDto);	
				outDto.put("success", new Boolean(true));
			}catch (Exception e){
				outDto.put("success", new Boolean(false));
				outDto.put("error",e.getMessage());
			}
			
			outDto.put("msg", "面积添加成功!");
			return outDto;
		}
		public Dto importErrorHouse(Dto pDto) {
			Dto outDto = new BaseDto();	
			g4Dao.insert("importErrorHouse", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "用户添加成功!");
			return outDto;
		}
		/**
		 * 修改面积(页面操作)
		 * @param pDto
		 * @return
		 */	
		public Dto updateArea(Dto pDto) {
			Dto outDto = new BaseDto();
			g4Dao.update("updateArea", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "面积修改成功!");
			return outDto;
		}
		/**
		 * 添加面积(页面操作)
		 * @param pDto
		 * @return
		 */	
		public Dto insertAreas(Dto pDto) {
			Dto outDto = new BaseDto();
			g4Dao.insert("insertAreas", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "面积添加成功!");
			return outDto;
		}
		/**
		 * 删除面积
		 * @param pDto
		 * @return
		 */	
		public Dto deleteAreaSingle(Dto pDto) {
			Dto outDto = new BaseDto();
			
			Integer countInteger = (Integer) g4Dao.queryForObject("areaHaveNotcharge", pDto);
			//System.out.println(g4Dao.queryForObject("haveNotcharge", pDto));
			if (countInteger.intValue() > 0) {
				outDto.put("success", new Boolean(false));
				outDto.put("msg", (String)pDto.get("house_code")+(String)pDto.get("owner_name")+",该用户面积"+pDto.getAsString("area_id")+"有应收,不能删除!请先删除该用户应收!");
				return outDto;
			}
			g4Dao.delete("deleteArea", pDto);	
			g4Dao.delete("deleteArea", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "面积"+(pDto.getAsInteger("area_id")+1)+"删除成功!");
			return outDto;
		}
		
		
		/**
		 * 阀门号导入，房间更新
		 * @param pDto
		 * @return
		 */
		public Dto updateHouseFamen(Dto pDto) {
			Dto outDto = new BaseDto();
			Integer countInteger = (Integer) g4Dao.queryForObject("haveFamenData", pDto);
			if (countInteger.intValue() > 0) {
				outDto.put("success", new Boolean(false));
				outDto.put("msg", (String)"编号为"+pDto.get("house_code")+pDto.get("valvecode")+"阀门号已存在，未导入。");
				return outDto;
			}
			g4Dao.update("updateHouseFamen", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "阀门号添加成功!");
			return outDto;
		}

		public Dto importErrorFamen(Dto pDto) {
			Dto outDto = new BaseDto();	
			g4Dao.insert("importErrorFamen", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "阀门号导入成功!");
			return outDto;
		}

}
