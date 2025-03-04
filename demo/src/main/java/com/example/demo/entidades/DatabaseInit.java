package com.example.demo.entidades;

import com.example.demo.entidades.Administrador;
import com.example.demo.repositorio.AdministradorRepository;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private ClienteRepository   clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
            administradorRepository.save(new Administrador("Juan Jose Bermudez", "thejuanjo1128@gmail.com", "123456"));
            
             // Añadir un cliente
            clienteRepository.save(new Cliente("Carlos", "Perez", "molokojj09@gmail.com", "bermu123", "1234567890", "Calle Falsa 123"));
            
            // Entradas (5)
            productoRepository.save(new Producto("Arepa de Maiz", 8.00f, "Deliciosas arepas rellenas de maíz fresco.", "/images/entrada1.png"));
            productoRepository.save(new Producto("Chichanorrada", 18.00f, "Crujientes trozos de cerdo fritos hasta alcanzar el punto perfecto de dorado y jugosidad.", "/images/entrada2.png"));
            productoRepository.save(new Producto("Empanadas", 12.00f, "nuestras empanadas rellenas de carne, pollo o queso te ofrecen un sabor auténtico en cada bocado.", "bruschetta.jpg"));
            productoRepository.save(new Producto("Chorizada", 18.00f, "Sabroso chorizo artesanal con el toque justo de especias.", "bruschetta.jpg"));
            productoRepository.save(new Producto("Yucas", 8.00f, "Trozos de yuca fritos hasta quedar dorados y crujientes.", "bruschetta.jpg"));


        // Platos Fuertes (5)
            productoRepository.save(new Producto("Tomahawk", 62.00f, "Un impresionante corte de carne a la parrilla, con hueso largo y jugoso, ideal para los amantes de la carne roja.", "/images/pf1.png"));
            productoRepository.save(new Producto("BabyBeef", 48.00f, "Corte tierno y jugoso de carne, cuidadosamente asado para ofrecer una textura suave y un sabor delicioso que se derrite en la boca.", "/images/pf2.png"));
            productoRepository.save(new Producto("Churrasco", 48.00f, "Corte de res perfectamente sazonado y asado a la parrilla, que resalta su sabor natural con una jugosidad inigualable.", "/images/pf3.png"));
            productoRepository.save(new Producto("CostillasBBQ", 58.00f, "Costillas de cerdo marinadas en una salsa barbacoa casera, cocinadas a fuego lento hasta quedar tiernas y con un sabor ahumado delicioso.", "/images/pf4.png"));
            productoRepository.save(new Producto("LomoDeRes", 48.00f, "Un corte fino de res, tierno y jugoso, asado a la perfección para resaltar su sabor natural. Ideal para los que buscan calidad en cada bocado.", "/images/pf5.png"));


        // Postres (4)
            productoRepository.save(new Producto("Maracuyoso", 12.00f, "Una dulce y refrescante crema de maracuyá que combina el sabor ácido de la fruta con la suavidad de la crema, un final perfecto para tu comida.", "bruschetta.jpg"));
            productoRepository.save(new Producto("Limonoso", 12.00f, "Delicioso postre de limón, refrescante y con un toque ácido perfecto para equilibrar cualquier comida, ideal para los amantes de los sabores cítricos.", "bruschetta.jpg"));
            productoRepository.save(new Producto("Flan de Coco", 12.00f, "Suave flan con un toque de coco rallado, cremoso y con la dulzura justa, que hará que tu paladar disfrute cada cucharada.", "bruschetta.jpg"));
            productoRepository.save(new Producto("Miloso", 12.00f, "Un postre cremoso con el sabor único del Milo, un toque de chocolate con malta que te hará recordar los sabores de la infancia.", "bruschetta.jpg"));


        // Adicionales (7)
        productoRepository.save(new Producto("Papa Casco", 8.00f, "Papas al estilo tradicional, servidas en su cáscara, fritas hasta quedar doradas y crujientes, una opción sabrosa para acompañar tu plato.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Papas francesas", 8.00f, "Papas fritas en corte fino, crujientes por fuera y suaves por dentro, perfectas para acompañar cualquier plato o disfrutar solas.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Yuquitas", 8.00f, "Papas fritas en corte fino, crujientes por fuera y suaves por dentro, perfectas para acompañar cualquier plato o disfrutar solas.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Papa Horneada", 12.00f, "Papas al horno, suaves por dentro y ligeramente crujientes por fuera, perfectas para acompañar cualquiera de nuestras carnes.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Chimichurri", 12.00f, "Salsa fresca a base de ajo, perejil, vinagre y aceite, el acompañamiento perfecto para realzar el sabor de tu carne a la parrilla.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Arroz", 12.00f, "Arroz esponjoso, ligeramente sazonado, un acompañante clásico que nunca puede faltar en nuestra mesa.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Guacamole", 8.00f, "Crema suave y fresca de aguacate con el toque perfecto de limón, cebolla y cilantro. Ideal para acompañar nuestros platos y disfrutar con totopos.", "bruschetta.jpg"));


        // Bebidas (6)
        productoRepository.save(new Producto("Coca cola", 5.00f, "La refrescante bebida clásica que no puede faltar, con su sabor único y burbujeante, perfecta para acompañar tu comida.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Coca cola zero", 5.00f, "La opción sin calorías, con el sabor refrescante y delicioso de la Coca-Cola original, ideal para quienes prefieren evitar el azúcar.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Sprite", 5.00f, "Bebida refrescante y cítrica, con el sabor burbujeante de limón-lima, ideal para disfrutar en cualquier momento.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Coctel de maracuya", 28.00f, "Refrescante cóctel con el toque tropical y ácido del maracuyá, combinado con ingredientes naturales que harán de tu bebida un verdadero placer.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Agua sin gas", 5.00f, "Agua fresca y purificada, la opción más natural y saludable para acompañar tu comida.", "bruschetta.jpg"));
        productoRepository.save(new Producto("Coctel Frutos rojos", 28.00f, "Una mezcla vibrante de frutos rojos frescos, ideal para los que disfrutan de una bebida afrutada y ligeramente dulce.", "bruschetta.jpg"));

        }
    }

