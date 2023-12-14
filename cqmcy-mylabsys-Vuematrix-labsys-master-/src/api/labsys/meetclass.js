import request from '@/utils/request'

// 查询实验室类型列表
export function listMeetclass(data) {
  return request({
    url: '/labsys/meetclass/list',
    method: 'post',
    data: data
  })
}

export function treeMeetclass() {
  return request({
    url: '/labsys/meetclass/tree',
    method: 'get'
  })
}

// 查询实验室类型详细
export function getMeetclass(id) {
  return request({
    url: '/labsys/meetclass/' + id,
    method: 'get'
  })
}

// 新增实验室类型
export function addMeetclass(data) {
  return request({
    url: '/labsys/meetclass',
    method: 'post',
    data: data
  })
}

// 修改实验室类型
export function uptMeetclass(data) {
  return request({
    url: '/labsys/meetclass',
    method: 'put',
    data: data
  })
}

// 删除实验室类型
export function delMeetclass(ids) {
  return request({
    url: '/labsys/meetclass/' + ids,
    method: 'delete'
  })
}

// 导出实验室类型
export function exptMeetclass(data) {
  return request({
    url: '/labsys/meetclass/export',
    method: 'post',
    data: data
  })
}
