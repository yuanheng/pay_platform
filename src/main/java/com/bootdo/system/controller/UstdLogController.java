package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.USDTLogStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.UstdLogDO;
import com.bootdo.system.service.UstdLogService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import static com.bootdo.common.utils.ShiroUtils.getUser;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 19:37:54
 */

@Controller
@RequestMapping("/system/ustdLog")
public class UstdLogController {
  @Autowired
  private UstdLogService ustdLogService;
  @Autowired
  private RedisUtils redisUtils;

  @GetMapping()
  String UstdLog() {
    return "system/ustdLog/ustdLog";
  }

  @ResponseBody
  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    //查询列表数据
    Query query = new Query(params);
    List<UstdLogDO> ustdLogList = ustdLogService.list(query);
    if (ustdLogList != null && ustdLogList.size() > 0) {
      for (UstdLogDO u : ustdLogList) {
        u.setStatusDesc(USDTLogStatusEnum.getTypeDescById(u.getStatus()));
      }
    }
    int total = ustdLogService.count(query);
    PageUtils pageUtils = new PageUtils(ustdLogList, total);
    return pageUtils;
  }


  @GetMapping("/edit/{id}")
  String edit(@PathVariable("id") Long id, Model model) {
    UstdLogDO ustdLog = ustdLogService.get(id);
    model.addAttribute("ustdLog", ustdLog);
    return "system/ustdLog/edit";
  }

  /**
   * 保存
   */
  @ResponseBody
  @PostMapping("/save")
  public R save(UstdLogDO ustdLog) {
    if (ustdLogService.save(ustdLog) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 修改
   */
  @ResponseBody
  @RequestMapping("/update")
  public R update(UstdLogDO ustdLog) {
    ustdLogService.update(ustdLog);
    return R.ok();
  }

  /**
   * 删除
   */
  @PostMapping("/auditing")
  @ResponseBody
  public R auditing(Long id, int status) {
    if(status > USDTLogStatusEnum.transfered.getId()){
      return R.error();
    }
    int result = ustdLogService.auditing(id,status,getUser().getUsername());
    if (result > 0) {
      UstdLogDO temp = ustdLogService.get(id);
      if (temp.getStatus() - USDTLogStatusEnum.transfered.getId() == 0){
        String tradeNo = temp.getNo();
        //在缓存中存储 ，防止重复支付回调。
        redisUtils.del(tradeNo);
        redisUtils.del(Constants.PAY_MID_PRE + "_"+temp.getMid());
      }
      return R.ok();
    }
    return R.error();
  }


  /**
   * 删除
   */
  @PostMapping("/remove")
  @ResponseBody
  public R remove(Long id) {
    if (ustdLogService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 删除
   */
  @PostMapping("/batchRemove")
  @ResponseBody
  public R remove(@RequestParam("ids[]") Long[] ids) {
    ustdLogService.batchRemove(ids);
    return R.ok();
  }

}
