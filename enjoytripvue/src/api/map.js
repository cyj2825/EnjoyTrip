import { localAxios } from "@/util/http-commons";

const local = localAxios(); 

const base_url = "hotPlace";

function sidoList(success,fail){
    local.get(`${base_url}/getSido`).then(success).catch(fail);
}
function gugunList(sido_code, success,fail){
    local.get(`${base_url}/getGugun?sidoCode=${sido_code}`).then(success).catch(fail);
}

function attList(params, success,fail){
    local.get(`${base_url}/getMarker`,{params: params}).then(success).catch(fail);
}

export{
    sidoList,
    gugunList,
    attList
};