import { localAxios } from "@/util/http-commons";

const local = localAxios(); // axios instance

const url = "/comment";
function listComment(articleno, success, fail) {
    local.get(`${url}/${articleno}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
    console.log("comment.js comment", comment);
    local.post(`${url}`, JSON.stringify(comment)).then(success).catch(fail);
}
function modifyComment(comment, success, fail) {
    local.put(`${url}`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteComment(articleno, commentNo, success, fail) {
    local.delete(`${url}/${articleno}/${commentNo}`).then(success).catch(fail);
}

export {
    listComment,
    writeComment,
    modifyComment,
    deleteComment
};
