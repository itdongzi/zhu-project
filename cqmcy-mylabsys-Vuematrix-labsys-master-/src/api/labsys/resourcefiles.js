import request from '@/utils/request'

// 查询教学资源文件列表
export function listResourcefiles(data) {
  return request({
    url: '/labsys/resourcefiles/list',
    method: 'post',
    data: data
  })
}

// 查询教学资源文件详细
export function getResourcefiles(id) {
  return request({
    url: '/labsys/resourcefiles/' + id,
    method: 'get'
  })
}

// 新增教学资源文件
export function addResourcefiles(data) {
  return request({
    url: '/labsys/resourcefiles',
    method: 'post',
    data: data
  })
}

// 修改教学资源文件
export function uptResourcefiles(data) {
  return request({
    url: '/labsys/resourcefiles',
    method: 'put',
    data: data
  })
}

// 删除教学资源文件
export function delResourcefiles(ids) {
  return request({
    url: '/labsys/resourcefiles/' + ids,
    method: 'delete'
  })
}

// 导出教学资源文件
export function exptResourcefiles(data) {
  return request({
    url: '/labsys/resourcefiles/export',
    method: 'post',
    data: data
  })
}