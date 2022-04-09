package cn.edu.xidian.cs1301.userdemo.services;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

import java.util.Map;

/**
 * @program: ofbiz
 * @description:
 * @author: chhi
 * @create: 2022-04-09 16:05
 */
public class UserDemoServices {

    public static final String MODULE = UserDemoServices.class.getName();

    public static Map<String, Object> createUserDemo(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        try {
            GenericValue userDemo = delegator.makeValue("UserDemo");
            // 设置自增id
            userDemo.setNextSeqId();
            // 从context map中设置所有非主键值
            userDemo.setNonPKFields(context);
            // 在数据库中创建记录(insert)
            userDemo = delegator.create(userDemo);
            result.put("userDemoId", userDemo.getString("userDemoId"));
            Debug.log("成功创建UserDemo， id=： {}", userDemo.getString("userDemoId"));
        } catch (GenericEntityException e) {
            Debug.logError(e, MODULE);
            return ServiceUtil.returnError("Error in creating record in UserDemo Entity......" + MODULE);
        }
        return result;
    }
}