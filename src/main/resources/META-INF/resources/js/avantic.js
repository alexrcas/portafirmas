const showValidations = validations => {
    const alertBox = document.querySelector('#avaModal #avaModalConstraints .message');
    alertBox.style.display = 'block';
    alertBox.setHTML(JSON.stringify(validations));

    let str = '';
    validations
        .map(val => val.message)
        .forEach(val => {
           str += `<span>${val}</span><br>`
        });

    alertBox.setHTML(str);
}

const showGenericServerErrorMessage = text => {
    const alertBox = document.querySelector('#avaModal #avaModalConstraints .message');
    alertBox.style.display = 'block';
    alertBox.setHTML(text);
}


const sendModalForm = async (url, modal) => {
    const form = document.querySelector('#avaModal form');

    let formBody = [];
    form.querySelectorAll('input[type=text]').forEach(input => {
        const key = encodeURIComponent(input.name);
        const value = encodeURIComponent(input.value);
        formBody.push(`${key}=${value}`);
    });

    form.querySelectorAll('input[type=datetime-local]').forEach(input => {
        const key = encodeURIComponent(input.name);
        const value = encodeURIComponent(input.value);
        formBody.push(`${key}=${value}`);
    });

    form.querySelectorAll('input[type=checkbox]').forEach(input => {
        const key = encodeURIComponent(input.name);
        const value = encodeURIComponent(input.checked);
        formBody.push(`${key}=${value}`);
    });

    Array.from(form.querySelectorAll('input[type=radio]'))
        .filter(input => input.checked)
        .forEach(input => {
            const key = encodeURIComponent(input.name);
            const value = encodeURIComponent(input.value);
            formBody.push(`${key}=${value}`);
        })

    form.querySelectorAll('select').forEach(input => {
        const key = encodeURIComponent(input.name);
        const value = encodeURIComponent(input.value);
        formBody.push(`${key}=${value}`);
    });

    form.querySelectorAll('textarea').forEach(input => {
        const key = encodeURIComponent(input.name);
        const value = encodeURIComponent(input.value);
        formBody.push(`${key}=${value}`);
    });

    form.querySelectorAll('.list-mapper').forEach(mapper => {
        const key = encodeURIComponent(mapper.id);
        if (mapper.dataset.values === '') { return }
        const values = JSON.parse(mapper.dataset.values);
        let list = values.map(v => `${key}=${v}`)
        formBody.push(list.join('&'));
    });


    formBody = formBody.join('&');

        const response = await fetch(url.split('?')[0], {
            method: 'POST',
            headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: formBody
        })

        if (response.ok) {
            modal.hide();
            document.location.reload();
        }

        if (response.status === 500) {
            const message = await response.text();
            showGenericServerErrorMessage(message);
            return;
        }

        try {
        const validations = await response.json();
        showValidations(validations);
        } catch (err) {
            console.log('No se devuelven validaciones para mostrar')
        }
}


const sendMultipartForm = async (url, modal) => {
    const form = document.querySelector('#avaModal form');

    const formData = new FormData(form);
    const response = await fetch(url, {
        method: 'POST',
        body: formData
    });

    if (response.ok) {
        modal.hide();
        document.location.reload();
    }

    if (response.status === 500) {
        const message = await response.text();
        showGenericServerErrorMessage(message);
        return;
    }

    try {
        const validations = await response.json();
        showValidations(validations);
    } catch (err) {
        console.log('No se devuelven validaciones para mostrar')
    }
}


const applyRequiredFieldStyle = (form, name) => {
    const field = form.querySelector(`input[name=${name}]`);
    if (field != null) {
        field.classList.toggle('required')
    }

    const select = form.querySelector(`select[name=${name}`);
    if (select != null) {
        select.classList.toggle('required')
    }
}

const processFormFields = () => {

    const form = document.querySelector('#avaModal form');
    if (form.querySelector('input') == null) { return; }

    const fields = JSON.parse(document.querySelector('#command').dataset.fields)

    form.querySelectorAll('input[type=text]').forEach(input => {
        input.value = fields[input.name];
    });

    form.querySelectorAll('input[type=radio]').forEach(input => {
        if (input.value === fields[input.name]) {
            input.checked = true;
        }
    });

    form.querySelectorAll('input[type=datetime-local]').forEach(input => {
        input.value = fields[input.name];
    });

    form.querySelectorAll('input[type=checkbox]').forEach(input => {
        input.checked = fields[input.name];
    });

    form.querySelectorAll('textarea').forEach(input => {
        input.value = fields[input.name];
    })

    form.querySelectorAll('select').forEach(select => {
        select.value = fields[select.name];
    });

    const propertiesDescriptors = JSON.parse(document.querySelector('#command').dataset.required);

    propertiesDescriptors.forEach(prop => {
        const name = prop.propertyName;
        const isRequiredField = prop.constraintDescriptors
            .some(constraint => constraint.annotationType.includes('NotBlank') || constraint.annotationType.includes('NotNull'))

        if (isRequiredField) {
            applyRequiredFieldStyle(form, name)
        }
    })
}

const processAutofocus = () => {
    const form = document.querySelector('#avaModal form');
    if (form.querySelector('input') == null) { return; }

    const el = form.querySelector('.autofocus');
    if (el == null) { return; }

    el.setAttribute('tabindex', '0');
    el.focus();
}

const fetchModal = async(url) => {
    const response = await fetch(url);
    const text = await response.text();

    const el = document.querySelector('#avaModal');;

    const modal = new bootstrap.Modal(el, {});

    const avaModalContent = document.querySelector('#avaModalContent');
    avaModalContent.innerHTML = text;

    const modalTitle = document.querySelector('.modalTitle');
    document.querySelector('#avaModalTitle').innerHTML = modalTitle.textContent;
    modalTitle.style.display = 'none';

    const submitButton = document.querySelector('#submit-modal-btn');
    if (submitButton != null) {
        submitButton.onclick = () => {
            sendModalForm(url, modal);
        };
    }

    const multipartButton = document.querySelector('#multipart-submit');
    if (multipartButton != null) {
        multipartButton.onclick = () => {
            sendMultipartForm(url, modal);
        }
    }

    const alertBox = document.querySelector('#avaModal #avaModalConstraints .message');
    alertBox.style.display = 'none';
    processFormFields();

    el.addEventListener('shown.bs.modal', () => {
        processAutofocus();
    }, {once: true});

    modal.show();

    const script = document.createElement('script');
    if (avaModalContent.querySelector('script') == null) { return }

    script.innerHTML = avaModalContent.querySelector('script').innerText;
    document.body.appendChild(script)
}



