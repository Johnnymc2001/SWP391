<div class="loader-wrapper">
    <div class="loadingio-spinner-cube-bkl74oanq2b">
        <div class="ldio-5pkshz2jr6j">
            <div></div><div></div><div></div><div></div>
        </div>
    </div>
    <style type="text/css">
        .loader-wrapper{
            width: 100%;
            height: 100%;
            position: absolute;
            background: white;
            top: 0;
            left: 0;
            z-index: 10000;
        }
        @keyframes ldio-5pkshz2jr6j {
            0% { transform: scale(1.1500000000000001) }
            100% { transform: scale(1) }
        }
        .ldio-5pkshz2jr6j div {
            position: absolute;
            width: 80px;
            height: 80px;
            top: 13.333333333333336px;
            left: 13.333333333333336px;
            background: #e15b64;
            animation: ldio-5pkshz2jr6j 1s cubic-bezier(0,0.5,0.5,1) infinite;
            animation-delay: -0.3s;
        }
        .ldio-5pkshz2jr6j div:nth-child(2) {
            top: 13.333333333333336px;
            left: 106.66666666666666px;
            background: #f47e60;
            animation-delay: -0.2s;
        }
        .ldio-5pkshz2jr6j div:nth-child(3) {
            top: 106.66666666666666px;
            left: 13.333333333333336px;
            background: #abbd81;
            animation-delay: 0s;
        }
        .ldio-5pkshz2jr6j div:nth-child(4) {
            top: 106.66666666666666px;
            left: 106.66666666666666px;
            background: #f8b26a;
            animation-delay: -0.1s;
        }
        .loadingio-spinner-cube-bkl74oanq2b {
            width: 200px;
            height: 200px;
            display: inline-block;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            overflow: hidden;
            background: #ffffff;
        }
        .ldio-5pkshz2jr6j {
            width: 100%;
            height: 100%;
            position: relative;
            transform: translateZ(0) scale(1);
            backface-visibility: hidden;
            transform-origin: 0 0;
        }
        .ldio-5pkshz2jr6j div { box-sizing: content-box; }
    </style>
</div>
<script>
    let loading = document.querySelector('.loader-wrapper');
    window.addEventListener('load', function(){
       loading.style.display = 'none'; 
    });
</script>