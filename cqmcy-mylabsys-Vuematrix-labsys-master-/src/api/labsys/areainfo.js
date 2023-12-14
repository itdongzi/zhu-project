import request from '@/utils/request'

// 查询实验楼区域列表
export function listAreainfo(data) {
  return request({
    url: '/labsys/areainfo/list',
    method: 'post',
    data: data
  })
}
 
export function treeAreainfo() {
  return request({
    url: '/labsys/areainfo/tree',
    method: 'get'
  })
}

// 查询实验楼区域详细
export function getAreainfo(id) {
  return request({
    url: '/labsys/areainfo/' + id,
    method: 'get'
  })
}

// 新增实验楼区域
export function addAreainfo(data) {
  return request({
    url: '/labsys/areainfo',
    method: 'post',
    data: data
  })
}

// 修改实验楼区域
export function uptAreainfo(data) {
  return request({
    url: '/labsys/areainfo',
    method: 'put',
    data: data
  })
}

// 删除实验楼区域
export function delAreainfo(ids) {
  return request({
    url: '/labsys/areainfo/' + ids,
    method: 'delete'
  })
}

// 导出实验楼区域
export function exptAreainfo(data) {
  return request({
    url: '/labsys/areainfo/export',
    method: 'post',
    data: data
  })
}