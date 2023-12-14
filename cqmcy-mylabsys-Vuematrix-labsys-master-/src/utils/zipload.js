import axios from 'axios'
import { getToken } from '@/utils/auth'

const baseUrl = process.env.VUE_APP_BASE_API
const mimeMap = {
  xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
  zip: 'application/zip'
}

// 通用下载方法 flag 是否下载之后就删除源文件
export function download (fileName,flag) {
  window.location.href = baseUrl + '/web/download?fileName=' + encodeURI(fileName) + '&delete=' + flag
}

export function downloadZip (str, filename) {
  var url = baseUrl + str
  axios({
    method: 'get',
    url: url,
    responseType: 'blob',
    headers: { 'Authorization': 'Bearer ' + getToken() }
  }).then(res => {
    resolveBlob(res, mimeMap.zip)
  })
}

/**
 * 解析blob响应内容并下载
 * @param {*} res blob响应内容
 * @param {String} mimeType MIME类型
 */
export function resolveBlob (res, mimeType) {
  const aLink = document.createElement('a')
  var blob = new Blob([res.data], { type: mimeType })
  // //从response的headers中获取filename, 后端response.setHeader("Content-disposition", "attachment; filename=xxxx.docx") 设置的文件名;
  var patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
  var contentDisposition = decodeURI(res.headers['content-disposition'])
  var result = patt.exec(contentDisposition)
  var fileName = result[1]
  // eslint-disable-next-line no-useless-escape
  fileName = fileName.replace(/\"/g, '')
  aLink.href = URL.createObjectURL(blob)
  aLink.setAttribute('download', fileName) // 设置下载文件名称
  document.body.appendChild(aLink)
  aLink.click()
  document.body.appendChild(aLink)
}
