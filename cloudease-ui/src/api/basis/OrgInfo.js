import request from '@/utils/request'

// 查询小区组织结构列表
export function listOrgInfo(query) {
  return request({
    url: '/basis/OrgInfo/list',
    method: 'get',
    params: query
  })
}

// 查询小区组织结构详细
export function getOrgInfo(communityOrgCode) {
  return request({
    url: '/basis/OrgInfo/' + communityOrgCode,
    method: 'get'
  })
}

// 新增小区组织结构
export function addOrgInfo(data) {
  return request({
    url: '/basis/OrgInfo',
    method: 'post',
    data: data
  })
}

// 修改小区组织结构
export function updateOrgInfo(data) {
  return request({
    url: '/basis/OrgInfo',
    method: 'put',
    data: data
  })
}

// 删除小区组织结构
export function delOrgInfo(communityOrgCode) {
  return request({
    url: '/basis/OrgInfo/' + communityOrgCode,
    method: 'delete'
  })
}
