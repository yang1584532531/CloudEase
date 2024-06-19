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
import com.cloudease.basis.domain.CommunityOrgInfo;
import com.cloudease.basis.service.ICommunityOrgInfoService;
import com.cloudease.common.utils.poi.ExcelUtil;
import com.cloudease.common.core.page.TableDataInfo;

/**
 * 小区组织结构Controller
 * 
 * @author yangchengjie
 * @date 2024-06-19
 */
@RestController
@RequestMapping("/basis/OrgInfo")
public class CommunityOrgInfoController extends BaseController
{
    @Autowired
    private ICommunityOrgInfoService communityOrgInfoService;

    /**
     * 查询小区组织结构列表
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:list')")
    @GetMapping("/list")
    public AjaxResult list(CommunityOrgInfo communityOrgInfo)
    {
//        startPage();
        List<CommunityOrgInfo> list = communityOrgInfoService.selectCommunityOrgInfoList(communityOrgInfo);
        return success(list);
    }

    /**
     * 导出小区组织结构列表
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:export')")
    @Log(title = "小区组织结构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityOrgInfo communityOrgInfo)
    {
        List<CommunityOrgInfo> list = communityOrgInfoService.selectCommunityOrgInfoList(communityOrgInfo);
        ExcelUtil<CommunityOrgInfo> util = new ExcelUtil<CommunityOrgInfo>(CommunityOrgInfo.class);
        util.exportExcel(response, list, "小区组织结构数据");
    }

    /**
     * 获取小区组织结构详细信息
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:query')")
    @GetMapping(value = "/{communityOrgId}")
    public AjaxResult getInfo(@PathVariable("communityOrgId") Long communityOrgId)
    {
        return success(communityOrgInfoService.selectCommunityOrgInfoBycommunityOrgId(communityOrgId));
    }

    /**
     * 新增小区组织结构
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:add')")
    @Log(title = "小区组织结构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityOrgInfo communityOrgInfo)
    {
        return toAjax(communityOrgInfoService.insertCommunityOrgInfo(communityOrgInfo));
    }

    /**
     * 修改小区组织结构
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:edit')")
    @Log(title = "小区组织结构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityOrgInfo communityOrgInfo)
    {
        return toAjax(communityOrgInfoService.updateCommunityOrgInfo(communityOrgInfo));
    }

    /**
     * 删除小区组织结构
     */
    @PreAuthorize("@ss.hasPermi('basis:OrgInfo:remove')")
    @Log(title = "小区组织结构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{communityOrgIds}")
    public AjaxResult remove(@PathVariable Long[] communityOrgIds)
    {
        return toAjax(communityOrgInfoService.deleteCommunityOrgInfoBycommunityOrgIds(communityOrgIds));
    }
}
