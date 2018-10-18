package org.module.sys.util.idgenerator;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.util.idgenerator.DBSequenceStorer;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.id.SequenceStorer;
import org.eredlab.g4.ccl.id.fomater.DefaultSequenceFormater;
import org.eredlab.g4.ccl.id.generator.DefaultIDGenerator;
import org.eredlab.g4.ccl.id.sequence.DefaultSequenceGenerator;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;

/**
 * ID生成器
 * @author XiongChun
 * @since 2009-07-13
 */
public class IdGenerator {
	private static Log log = LogFactory.getLog(IdGenerator.class);
	private static int catche = 1;
	private static IDao g4Dao = (IDao)SpringBeanLoader.getSpringBean("g4Dao");
	
	/**
	 * 字段名称
	 */
	private String fieldname;
	
	public IdGenerator(String pFieldName){
		setFieldname(pFieldName);
	}
	
	public IdGenerator(){
	}
	
	/**
	 * 获取ID生成器实例
	 * @return DefaultIDGenerator
	 */
	public DefaultIDGenerator getDefaultIDGenerator(){
		Dto dto = new BaseDto();
		dto.put("fieldname", getFieldname());
		dto = (BaseDto)g4Dao.queryForObject("getEaSequenceByFieldName", dto);
		DefaultIDGenerator idGenerator = new DefaultIDGenerator(); 
		DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater(); 
		sequenceFormater.setPattern(dto.getAsString("pattern"));
		DefaultSequenceGenerator sequenceGenerator = new DefaultSequenceGenerator(getFieldname());
		SequenceStorer sequenceStorer = new DBSequenceStorer();
		sequenceGenerator.setSequenceStorer(sequenceStorer);
		sequenceGenerator.setCache(catche);
		idGenerator.setSequenceFormater(sequenceFormater);
		idGenerator.setSequenceGenerator(sequenceGenerator);
		return idGenerator;
	}
	
  
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
}
