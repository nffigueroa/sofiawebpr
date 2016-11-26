/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var status =0; // Indica que hay un item abierto localmente
var statusItem =0; // Indica que hay un item abierto globalmente
var nombreTag; // Se guarda el tag que se abre
function showItem(tag)
{
    if(statusItem ==1)
    {
        nombreTag.style.display ="none";
        statusItem= 0;
            if(nombreTag.attributes.id.nodeValue == tag.attributes.id.nodeValue) // Si se esta clicando sobre el mismo item que esta guardado
            {
                status = 1;
            }
            else
            {
                status =0;
            }
    }
    nombreTag = tag ;
        if(status == 0)
        {
            abrirItem(tag);
        }
        else
        {
           cerrarItem(tag);
        }
 }
 
 function cerrarItem(tag2)
 {
      tag2.style.display ="none";
      tag2.style.transition = "width 2s,height 0.06s linear";
      status = 0;
      statusItem=0;
 }
 
 function abrirItem(tag)
 {
      tag.style.display ="block";   
      tag.style.transition = "width 2s,height 0.06s linear";
      status = 1;
      statusItem =1;
 }