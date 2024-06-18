import request from '@/utils/request'

// 查询业主信息列表
export function listInfo(query) {
  return request({
    url: '/basis/info/list',
    method: 'get',
    params: query
  })
}

// 查询业主信息详细
export function getInfo(id) {
  return request({
    url: '/basis/info/' + id,
    method: 'get'
  })
}

// 新增业主信息
export function addInfo(data) {
  return request({
    url: '/basis/info',
    method: 'post',
    data: data
  })
}

// 修改业主信息
export function updateInfo(data) {
  return request({
    url: '/basis/info',
    method: 'put',
    data: data
  })
}

// 删除业主信息
export function delInfo(id) {
  return request({
    url: '/basis/info/' + id,
    method: 'delete'
  })
}
