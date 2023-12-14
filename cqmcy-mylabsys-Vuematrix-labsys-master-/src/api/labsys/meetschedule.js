import request from '@/utils/request'

// 查询实验室排课列表
export function listMeetschedule(data) {
  return request({
    url: '/labsys/meetschedule/list',
    method: 'post',
    data: data
  })
}

// 查询实验室排课详细
export function getMeetschedule(id) {
  return request({
    url: '/labsys/meetschedule/' + id,
    method: 'get'
  })
}

// 新增实验室排课
export function addMeetschedule(data) {
  return request({
    url: '/labsys/meetschedule',
    method: 'post',
    data: data
  })
}

// 修改实验室排课
export function uptMeetschedule(data) {
  return request({
    url: '/labsys/meetschedule',
    method: 'put',
    data: data
  })
}

// 删除实验室排课
export function delMeetschedule(ids) {
  return request({
    url: '/labsys/meetschedule/' + ids,
    method: 'delete'
  })
}

// 导出实验室排课
export function exptMeetschedule(data) {
  return request({
    url: '/labsys/meetschedule/export',
    method: 'post',
    data: data
  })
}

export function getXnxqbhList() {
  return request({
    url: '/labsys/meetschedule/getxnxqbhlist',
    method: 'get'
  })
}

export function getKkzcList(data) {
  return request({
    url: '/labsys/meetschedule/getkkzclist',
    method: 'post',
    data: data
  })
}
