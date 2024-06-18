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
import com.cloudease.basis.domain.LandlordInfo;
import com.cloudease.basis.service.ILandlordInfoService;
import com.cloudease.common.utils.poi.ExcelUtil;
import com.cloudease.common.core.page.TableDataInfo;

/**
 * 业主信息Controller
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/basis/info")
public class LandlordInfoController extends BaseController
{
    @Autowired
    private ILandlordInfoService landlordInfoService;

    /**
     * 查询业主信息列表
     */
    @PreAuthorize("@ss.hasPermi('basis:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(LandlordInfo landlordInfo)
    {
        startPage();
        List<LandlordInfo> list = landlordInfoService.selectLandlordInfoList(landlordInfo);
        return getDataTable(list);
    }

    /**
     * 导出业主信息列表
     */
    @PreAuthorize("@ss.hasPermi('basis:info:export')")
    @Log(title = "业主信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LandlordInfo landlordInfo)
    {
        List<LandlordInfo> list = landlordInfoService.selectLandlordInfoList(landlordInfo);
        ExcelUtil<LandlordInfo> util = new ExcelUtil<LandlordInfo>(LandlordInfo.class);
        util.exportExcel(response, list, "业主信息数据");
    }

    /**
     * 获取业主信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basis:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(landlordInfoService.selectLandlordInfoById(id));
    }

    /**
     * 新增业主信息
     */
    @PreAuthorize("@ss.hasPermi('basis:info:add')")
    @Log(title = "业主信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LandlordInfo landlordInfo)
    {
        landlordInfo.setCreateBy(getUsername());
        return toAjax(landlordInfoService.insertLandlordInfo(landlordInfo));
    }

    /**
     * 修改业主信息
     */
    @PreAuthorize("@ss.hasPermi('basis:info:edit')")
    @Log(title = "业主信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LandlordInfo landlordInfo)
    {
        return toAjax(landlordInfoService.updateLandlordInfo(landlordInfo));
    }

    /**
     * 删除业主信息
     */
    @PreAuthorize("@ss.hasPermi('basis:info:remove')")
    @Log(title = "业主信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(landlordInfoService.deleteLandlordInfoByIds(ids));
    }
}
