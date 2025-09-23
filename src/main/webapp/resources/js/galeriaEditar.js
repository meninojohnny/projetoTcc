/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    window.galeriaEditar = {
        init: initGaleria,
        show: showGaleria,
        close: close,
        rotate: rotate,
        gallerys: []
    };

    function initGaleria(id, saveFunction) {
        idName = id.trim();
        if (typeof (idName) != 'string') {
            console.error("Galeria should have a valid id name.")
            return;
        }

        let hasGallery = window.galeriaEditar.gallerys.filter(function (el) {
            if (el.id === id) {
                return true;
            }
            return false;
        })

        if (hasGallery.length !== 0) {
            console.error("Duplicated id, please choose another one.");
            return;
        }

        let element = document.getElementById(idName);

        element.addEventListener('keydown', function (ev) {
            if (event.isComposing || event.keyCode === 229) {
                return;
            }
            if (event.keyCode === 27) {
                //element.classList.remove('is-visible');
                close(idName);
            }
        })
        let panelElements = element.getElementsByClassName('galeriaEditar-panel');
        let canvas = null;
        let zoomwrap = null;
        if (panelElements.length > 0) {
            let closeButtons = panelElements[0].getElementsByClassName('pswp__button--close');
            let saveButtons = element.getElementsByClassName('galeriaEditar--rotate--save');
            let rotateLeftButtons = element.getElementsByClassName('galeriaEditar--rotate--left');
            let focarButtons = element.getElementsByClassName('pswp__button--focar');
            let rotateRightButtons = element.getElementsByClassName('galeriaEditar--rotate--right');
            canvas = element.getElementsByClassName("view-canvas");
            zoomwrap = element.getElementsByClassName("galeriaEditar-zoomwrap");
            if (canvas.length > 0) {
                canvas = canvas[0];
            } else {
                console.error("Canvas element is missing");
                return;
            }
            if (zoomwrap.length > 0) {
                zoomwrap = zoomwrap[0];
            } else {
                console.error("Zoom wrap element is missing");
                return;
            }
            if (closeButtons.length > 0) {
                let closeButton = closeButtons[0];
                closeButton.addEventListener('click', function (ev) {
                    //element.classList.remove('is-visible');
                    close(idName);
                })
            } else {
                console.error("galeriaEditar-panel element is missing.");
                return;
            }
            if (saveButtons.length > 0) {
                let saveButton = saveButtons[0];
                saveButton.addEventListener('click', function (ev) {
                    //saveFunction();
                    saveGaleria(idName);

                })
            } else {
                console.error("galeriaEditar-panel element is missing.");
                return;
            }
            if (rotateLeftButtons.length > 0) {
                let rotateLeftButton = rotateLeftButtons[0];
                rotateLeftButton.addEventListener('click', function (e) {
                    galeriaEditar.rotate(element, 'left');
                })
            } else {
                console.error("Missing rotate left button in element", element.id);
                return;
            }
            if (rotateLeftButtons.length > 0) {
                let rotateLeftButton = rotateLeftButtons[0];
                rotateLeftButton.addEventListener('click', function alterar(e) {
                    galeriaEditar.style.with = '200px';


                })
            } else {
                console.error("Missing rotate left button in element", element.id);
                return;
            }
            if (rotateRightButtons.length > 0) {
                let rotateRightButton = rotateRightButtons[0];
                rotateRightButton.addEventListener('click', function (e) {
                    galeriaEditar.rotate(element, 'right');
                })
            } else {
                console.error("Missing rotate right button in element", element.id);
                return;
            }
        } else {
            console.error("galeriaEditar-panel element is missing.");
            return;
        }

        if (canvas == null && zoomwrap == null) {
            console.error("Canvas or zoomwrap element is missing, could not init");
            return;
        }

        return window.galeriaEditar.gallerys.push({
            id: idName,
            idAnexo: -1,
            rootElement: element,
            canvasElement: canvas,
            angleRotation: 0,
            image: null,
            saveFunction: saveFunction
        });
    }

    function showGaleria(id, idAnexo, path) {
        if (typeof (id) !== 'string') {
            console.error("id argument should be a string");
            return;
        }
        if (typeof (idAnexo) !== 'number' || parseInt(idAnexo) < 0) {
            console.error("idAnexo argument should be a integer or range not valid.");
            return;
        }
        if (typeof (path) !== 'string') {
            console.error("path argument should be a string");
            return;
        }
        window.galeriaEditar.gallerys.filter(function (el) {
            if (el.id === id && !el.rootElement.classList.contains('is-visible')) {
                el.rootElement.classList.add('is-visible');
                el.rootElement.focus();
                el.idAnexo = idAnexo;
                let imgs = el.rootElement.getElementsByTagName("img");
                if (imgs.length > 0) {
                    imgs[0].src = path;
                    b = drawOnCanvas(el, imgs[0]);
                } else {
                    console.error("There is a gallery without img element, should be fixed.");
                }
                return true;
            }
            return false;
        });
    }

    function drawOnCanvas(el, imgElement) {
        let context = el.canvasElement.getContext("2d");
        context.clearRect(0, 0, el.canvasElement.width, el.canvasElement.height);
        if (!el.canvasElement.getContext) {
            console.error("Canvas not supported.");
            return;
        }
        el.image = new Image();
        el.image.src = imgElement.src;

        el.image.onload = function () {
            el.canvasElement.width = el.image.width;
            el.canvasElement.height = el.image.height;
            context.drawImage(el.image, 0, 0);
        };
        return context;
    }

    function rotate(el, direction) {
        let canvas = el.getElementsByClassName("view-canvas")[0];
        let context = canvas.getContext("2d");
        context.clearRect(0, 0, canvas.width, canvas.height);
        let gallery = this.gallerys.filter(function (value) {
            if (value.id === el.id) {
                return true;
            }
            return false;
        })[0];
        let newAngle = gallery.angleRotation;
        if (direction === 'left') {
            if (newAngle > 0) {
                newAngle = newAngle - 90;
            } else {
                newAngle = 270;
            }
            gallery.angleRotation = newAngle;
        } else if (direction === 'right') {
            if (newAngle < 270) {
                newAngle = newAngle + 90;
            } else {
                newAngle = 0;
            }
            gallery.angleRotation = newAngle;
        }
        //Draw origin
        context.save();

        if (newAngle === 0) {
            canvas.width = gallery.image.width;
            canvas.height = gallery.image.height;
            context.resetTransform();
        } else if (newAngle === 90) {
            canvas.width = gallery.image.height;
            canvas.height = gallery.image.width;
            context.rotate((Math.PI / 180) * newAngle);
            context.translate(0, -gallery.image.height);
        } else if (newAngle === 180) {
            canvas.width = gallery.image.width;
            canvas.height = gallery.image.height;
            context.rotate((Math.PI / 180) * newAngle);
            context.translate(-gallery.image.width, -gallery.image.height);
        } else if (newAngle === 270) {
            canvas.width = gallery.image.height;
            canvas.height = gallery.image.width;
            context.rotate((Math.PI / 180) * newAngle);
            context.translate(-gallery.image.width, 0);
        }

        context.drawImage(gallery.image, 0, 0)
        context.restore();
    }

    function close(idName) {
        let element = document.getElementById(idName);
        if (element !== null) {
            let gallerys = galeriaEditar.gallerys.filter((value) => {
                if (value.id === idName) {
                    return true;
                }
                return false;
            })
            if (gallerys.length > 0) {
                let gallery = gallerys[0];
                let ctx = gallery.canvasElement.getContext('2d');
                ctx.clearRect(0, 0, gallery.canvasElement.width, gallery.canvasElement.height);
                element.classList.remove('is-visible');
                gallery.angleRotation = 0;
                gallery.image = null;
                gallery.idAnexo = -1;
            }
        } else {
            console.error(idName, " element not found")
            return;
        }
    }

    function saveGaleria(idName) {
        let element = document.getElementById(idName);
        if (element !== null) {
            let gallerys = galeriaEditar.gallerys.filter((value) => {
                if (value.id === idName) {
                    return true;
                }
                return false;
            })
            if (gallerys.length > 0) {
                let gallery = gallerys[0];
                if (gallery.saveFunction !== null && gallery.saveFunction !== undefined) {
                    gallery.saveFunction(gallery.canvasElement.toDataURL());
                }
            }
        } else {
            console.error(idName, " element not found")
            return;
        }
    }

})();