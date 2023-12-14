import request from '@/utils/request'

// 查询安全试卷列表
export function listSafetypaper(data) {
  return request({
    url: '/labsys/safetypaper/list',
    method: 'post',
    data: data
  })
}

// 查询安全试卷详细
export function getSafetypaper(id) {
  return request({
    url: '/labsys/safetypaper/' + id,
    method: 'get'
  })
}

// 新增安全试卷
export function addSafetypaper(data) {
  return request({
    url: '/labsys/safetypaper',
    method: 'post',
    data: data
  })
}

// 修改安全试卷
export function uptSafetypaper(data) {
  return request({
    url: '/labsys/safetypaper',
    method: 'put',
    data: data
  })
}

// 删除安全试卷
export function delSafetypaper(ids) {
  return request({
    url: '/labsys/safetypaper/' + ids,
    method: 'delete'
  })
}

// 导出安全试卷
export function exptSafetypaper(data) {
  return request({
    url: '/labsys/safetypaper/export',
    method: 'post',
    data: data
  })
}