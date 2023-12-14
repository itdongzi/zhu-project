import request from '@/utils/request'

// 查询实验室排课列表
export function listRoomschedule(data) {
  return request({
    url: '/labsys/roomschedule/list',
    method: 'post',
    data: data
  })
}

// 查询实验室排课详细
export function getRoomschedule(id) {
  return request({
    url: '/labsys/roomschedule/' + id,
    method: 'get'
  })
}

// 新增实验室排课
export function addRoomschedule(data) {
  return request({
    url: '/labsys/roomschedule',
    method: 'post',
    data: data
  })
}

// 修改实验室排课
export function uptRoomschedule(data) {
  return request({
    url: '/labsys/roomschedule',
    method: 'put',
    data: data
  })
}

// 删除实验室排课
export function delRoomschedule(ids) {
  return request({
    url: '/labsys/roomschedule/' + ids,
    method: 'delete'
  })
}

// 导出实验室排课
export function exptRoomschedule(data) {
  return request({
    url: '/labsys/roomschedule/export',
    method: 'post',
    data: data
  })
}

export function getXnxqbhList() {
  return request({
    url: '/labsys/roomschedule/getxnxqbhlist',
    method: 'get'
  })
}

export function getKkzcList(data) {
  return request({
    url: '/labsys/roomschedule/getkkzclist',
    method: 'post',
    data: data
  })
}
