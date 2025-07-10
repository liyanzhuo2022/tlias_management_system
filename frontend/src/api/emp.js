import request from '@/utils/request'

export const queryPageApi = (name, gender, begin, end, page, pageSize) => 
  request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)

export const addApi = (emp) => 
  request.post('/emps', emp)

export const queryInfoApi = (id) => 
  request.get(`/emps/${id}`)

export const updateApi = (emp) => 
  request.put('/emps', emp)

export const deleteApi = (ids) =>
  request.delete('/emps', {
    params: {
      ids: Array.isArray(ids) ? ids : [ids]
    },
    paramsSerializer: {
      // 关键点：改成默认格式，而非数组风格
      indexes: null // ✅ 让 ids[]= 替换成 ids=
    }
  });


