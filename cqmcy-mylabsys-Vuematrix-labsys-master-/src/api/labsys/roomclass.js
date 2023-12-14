import request from '@/utils/request'

// 查询实验室类型列表
export function listRoomclass(data) {
  return request({
    url: '/labsys/roomclass/list',
    method: 'post',
    data: data
  })
}

export function treeRoomclass() {
  return request({
    url: '/labsys/roomclass/tree',
    method: 'get'
  })
}

// 查询实验室类型详细
export function getRoomclass(id) {
  return request({
    url: '/labsys/roomclass/' + id,
    method: 'get'
  })
}

// 新增实验室类型
export function addRoomclass(data) {
  return request({
    url: '/labsys/roomclass',
    method: 'post',
    data: data
  })
}

// 修改实验室类型
export function uptRoomclass(data) {
  return request({
    url: '/labsys/roomclass',
    method: 'put',
    data: data
  })
}

// 删除实验室类型
export function delRoomclass(ids) {
  return request({
    url: '/labsys/roomclass/' + ids,
    method: 'delete'
  })
}

// 导出实验室类型
export function exptRoomclass(data) {
  return request({
    url: '/labsys/roomclass/export',
    method: 'post',
    data: data
  })
}
