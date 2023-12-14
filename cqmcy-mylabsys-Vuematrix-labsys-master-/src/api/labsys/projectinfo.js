import request from '@/utils/request'

// 查询申报项目信息列表
export function listProjectinfo(data) {
  return request({
    url: '/labsys/projectinfo/list',
    method: 'post',
    data: data
  })
}

// 查询申报项目信息详细
export function getProjectinfo(id) {
  return request({
    url: '/labsys/projectinfo/' + id,
    method: 'get'
  })
}

// 新增申报项目信息
export function addProjectinfo(data) {
  return request({
    url: '/labsys/projectinfo',
    method: 'post',
    data: data
  })
}

// 修改申报项目信息
export function uptProjectinfo(data) {
  return request({
    url: '/labsys/projectinfo',
    method: 'put',
    data: data
  })
}

// 删除申报项目信息
export function delProjectinfo(ids) {
  return request({
    url: '/labsys/projectinfo/' + ids,
    method: 'delete'
  })
}

// 导出申报项目信息
export function exptProjectinfo(data) {
  return request({
    url: '/labsys/projectinfo/export',
    method: 'post',
    data: data
  })
}