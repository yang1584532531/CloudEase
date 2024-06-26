package com.cloudease.basis.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudease.common.annotation.Log;
import com.cloudease.common.core.controller.BaseController;
import com.cloudease.common.core.domain.AjaxResult;
import com.cloudease.common.enums.BusinessType;
import com.cloudease.basis.domain.CommunityInfo;
import com.cloudease.basis.service.ICommunityInfoService;
import com.cloudease.common.utils.poi.ExcelUtil;
import com.cloudease.common.core.page.TableDataInfo;

/**
 * 小区信息Controller
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/basis/communityInfo")
public class CommunityInfoController extends BaseController
{
    @Autowired
    private ICommunityInfoService communityInfoService;

    /**
     * 查询小区信息列表
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityInfo communityInfo)
    {
        startPage();
        List<CommunityInfo> list = communityInfoService.selectCommunityInfoList(communityInfo);
        return getDataTable(list);
    }

    /**
     * 导出小区信息列表
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:export')")
    @Log(title = "小区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityInfo communityInfo)
    {
        List<CommunityInfo> list = communityInfoService.selectCommunityInfoList(communityInfo);
        ExcelUtil<CommunityInfo> util = new ExcelUtil<CommunityInfo>(CommunityInfo.class);
        util.exportExcel(response, list, "小区信息数据");
    }

    /**
     * 获取小区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(communityInfoService.selectCommunityInfoById(id));
    }

    /**
     * 新增小区信息
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:add')")
    @Log(title = "小区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityInfo communityInfo)
    {
        communityInfo.setCreateBy(getUsername());
        return toAjax(communityInfoService.insertCommunityInfo(communityInfo));
    }

    /**
     * 修改小区信息
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:edit')")
    @Log(title = "小区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityInfo communityInfo)
    {
        return toAjax(communityInfoService.updateCommunityInfo(communityInfo));
    }

    /**
     * 删除小区信息
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:remove')")
    @Log(title = "小区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(communityInfoService.deleteCommunityInfoByIds(ids));
    }

    /**
     * 根据小区组织Id获取小区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basis:communityInfo:communityOrgCode')")
    @GetMapping(value = "/getInfoCommunityOrgCode/{communityOrgCode}")
    public AjaxResult getInfoCommunityOrgCode(@PathVariable("communityOrgCode") Long communityOrgCode)
    {
        CommunityInfo info = communityInfoService.selectCommunityInfoByCommunityOrgCode(communityOrgCode);
        if (info == null){
            return AjaxResult.error("未查询到该小区的详细信息，请直接新建",new CommunityInfo());
        } else {
            return AjaxResult.success(info);
        }

    }
}
