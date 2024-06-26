import request from '@/utils/request'

// 查询小区信息列表
export function listCommunityInfo(query) {
  return request({
    url: '/basis/communityInfo/list',
    method: 'get',
    params: query
  })
}

// 查询小区信息详细
export function getCommunityInfo(id) {
  return request({
    url: '/basis/communityInfo/' + id,
    method: 'get'
  })
}

// 新增小区信息
export function addCommunityInfo(data) {
  return request({
    url: '/basis/communityInfo',
    method: 'post',
    data: data
  })
}

// 修改小区信息
export function updateCommunityInfo(data) {
  return request({
    url: '/basis/communityInfo',
    method: 'put',
    data: data
  })
}

// 删除小区信息
export function delCommunityInfo(id) {
  return request({
    url: '/basis/communityInfo/' + id,
    method: 'delete'
  })
}

// 根据小区组织id查询详细信息
export function getInfoCommunityOrgCode(communityOrgCode) {
  return request({
    url: '/basis/communityInfo/getInfoCommunityOrgCode/' + communityOrgCode,
    method: 'get'
  })
}
