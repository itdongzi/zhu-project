import request from '@/utils/request'

// 查询实训成果列表
export function listAchieveinfo(data) {
  return request({
    url: '/labsys/achieveinfo/list',
    method: 'post',
    data: data
  })
}

// 查询实训成果详细
export function getAchieveinfo(id) {
  return request({
    url: '/labsys/achieveinfo/' + id,
    method: 'get'
  })
}

// 新增实训成果
export function addAchieveinfo(data) {
  return request({
    url: '/labsys/achieveinfo',
    method: 'post',
    data: data
  })
}

// 修改实训成果
export function uptAchieveinfo(data) {
  return request({
    url: '/labsys/achieveinfo',
    method: 'put',
    data: data
  })
}

// 删除实训成果
export function delAchieveinfo(ids) {
  return request({
    url: '/labsys/achieveinfo/' + ids,
    method: 'delete'
  })
}

// 导出实训成果
export function exptAchieveinfo(data) {
  return request({
    url: '/labsys/achieveinfo/export',
    method: 'post',
    data: data
  })
}