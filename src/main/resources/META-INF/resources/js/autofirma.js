AutoScript.cargarAppAfirma();
AutoScript.setServlets(Constants.URL_BASE_SERVICES + "/afirma-signature-storage/StorageService", Constants.URL_BASE_SERVICES + "/afirma-signature-retriever/RetrieveService");

const abrirAutofirma = async (contenidoDocumento) => {

    const el = document.querySelector('#autofirma-modal');
    const modal = new bootstrap.Modal(el, {})
    modal.show();
    document.querySelector('#avaModal').style.opacity = 0;
    document.querySelector('#firmar-btn').disabled = true;

    const firma = await doSign(contenidoDocumento);
    document.querySelector('#valorFirma').value = firma;

    document.querySelector('#autofirma-wait').style.display = 'none';
    document.querySelector('#autofirma-done').innerHTML = '<div class="check-circle"><div class="check-circle__mark"></div></div>'

    setTimeout(() => {
        document.querySelector('#submit-modal-btn').click();
    }, 1200)
}


const doSign = contenidoDocumento => {
    return new Promise((res, rej) => {
        const data = contenidoDocumento;
        AutoScript.sign(
            (data != undefined && data != null && data != "") ? data : null,
            'SHA512withRSA',
            'CAdES',
            'serverUrl=http://appprueba:8080/afirma-server-triphase-signer/SignatureService',
            (signatureB64, certificateB64, extraData) => {
                res(signatureB64);
            },
            () => {
                console.log('error')
            });
    });
}