const kanban = {
    /**
    * 작업 수정 팝업 처리 
    * 
    */
    handleEvent(e) {
        const id = e.currentTarget.dataset.id;
        const layerDim = document.createElement("div");
        layerDim.id = "layer_dim";
        document.body.appendChild(layerDim);
        layerDim.addEventListener("click", this.close);
        
        const layer = document.createElement("div");
        layer.id = "layer";
        document.body.appendChild(layer);
        
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "update?id=" + id);
        xhr.addEventListener("readystatechange", function() {
            if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
                layer.innerHTML = xhr.responseText;
            }
        });
        xhr.send(null);
    },
    /*
    * 팝업 닫기
    */
    close() {
        const els = document.querySelectorAll("#layer, #layer_dim");
        for (el of els) {
            el.parentElement.removeChild(el);
        }
    }
};



/** 이벤트 처리 S */
window.addEventListener("DOMContentLoaded", function() {
    frmDelete.addEventListener("submit", function(e) {
        if (!confirm('정말 삭제하시겠습니까?')) {
            e.preventDefault();
        }
    });
    
    /** 수정 버튼 클릭 처리 S */
    const editEls = document.getElementsByClassName("edit");
    for (el of editEls) {
        el.addEventListener("click", kanban);
    }
    /** 수정 버튼 클릭 처리 E */
});
/** 이벤트 처리 E */