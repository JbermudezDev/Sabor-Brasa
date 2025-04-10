package com.example.demo.entidades;

import com.example.demo.entidades.Administrador;
import com.example.demo.repositorio.AdicionalRepository;
import com.example.demo.repositorio.AdministradorRepository;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.DomiciliarioRepository;
import com.example.demo.repositorio.ProductoRepository;
import com.example.demo.repositorio.OperadorRepository;
import com.example.demo.repositorio.PedidoRepository;
import com.example.demo.entidades.Adicional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;
import java.util.Date;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private ClienteRepository   clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private AdicionalRepository adicionalRepository;

    @Autowired
    private DomiciliarioRepository domiciliarioRepository;

    @Autowired
    private OperadorRepository operadorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        
            administradorRepository.save(new Administrador("Juan Jose Bermudez", "thejuanjo1128@gmail.com", "123456"));
            
            // Crear y guardar clientes
            Cliente cliente1 = clienteRepository.save(new Cliente("Carlos", "Perez", "molokojj09@gmail.com", "bermu123", "1234567890", "Calle Falsa 123"));
            Cliente cliente2 = clienteRepository.save(new Cliente("Juan", "Bermudez", "joseber63@hotmail.com", "123456", "1234567890", "Calle Falsa 123"));

            // Crear y guardar pedidos asociados a los clientes
            Pedido pedido1 = new Pedido(new Date(), "Pendiente", cliente1);
            pedidoRepository.save(pedido1);

            Pedido pedido2 = new Pedido(new Date(), "En preparación", cliente1);
            pedidoRepository.save(pedido2);

            Pedido pedido3 = new Pedido(new Date(), "En camino", cliente1);
            pedidoRepository.save(pedido3);

            Pedido pedido4 = new Pedido(new Date(), "Entregado", cliente1);
            pedidoRepository.save(pedido4);

            Pedido pedido5 = new Pedido(new Date(), "Cancelado", cliente1);
            pedidoRepository.save(pedido5);

            Pedido pedido6 = new Pedido(new Date(), "Pendiente", cliente2);
            pedidoRepository.save(pedido6);

            Pedido pedido7 = new Pedido(new Date(), "En preparación", cliente2);
            pedidoRepository.save(pedido7);

            Pedido pedido8 = new Pedido(new Date(), "En camino", cliente2);
            pedidoRepository.save(pedido8);

            Pedido pedido9 = new Pedido(new Date(), "Entregado", cliente2);
            pedidoRepository.save(pedido9);

            Pedido pedido10 = new Pedido(new Date(), "Cancelado", cliente2);
            pedidoRepository.save(pedido10);
                        
             // Crear y guardar 20 domiciliarios
             domiciliarioRepository.save(new Domiciliario("Carlos Pérez", "3001234567", "1234567891", true));
             domiciliarioRepository.save(new Domiciliario("Ana Gómez", "3002345678", "1234567892", true));
             domiciliarioRepository.save(new Domiciliario("Luis Martínez", "3003456789", "1234567893", true));
             domiciliarioRepository.save(new Domiciliario("María Rodríguez", "3004567890", "1234567894", true));
             domiciliarioRepository.save(new Domiciliario("Jorge Ramírez", "3005678901", "1234567895", true));
             domiciliarioRepository.save(new Domiciliario("Sofía López", "3006789012", "1234567896", true));
             domiciliarioRepository.save(new Domiciliario("Andrés Torres", "3007890123", "1234567897", true));
             domiciliarioRepository.save(new Domiciliario("Laura Sánchez", "3008901234", "1234567898", true));
             domiciliarioRepository.save(new Domiciliario("Diego Castro", "3009012345", "1234567899", true));
             domiciliarioRepository.save(new Domiciliario("Valentina Morales", "3000123456", "1234567810", true));
             domiciliarioRepository.save(new Domiciliario("Camilo Vargas", "3001234500", "1234567811", true));
             domiciliarioRepository.save(new Domiciliario("Isabella Ruiz", "3002345600", "1234567812", true));
             domiciliarioRepository.save(new Domiciliario("Mateo Herrera", "3003456700", "1234567813", true));
             domiciliarioRepository.save(new Domiciliario("Daniela Mendoza", "3004567800", "1234567814", true));
             domiciliarioRepository.save(new Domiciliario("Sebastián Ortiz", "3005678900", "1234567815", true));
             domiciliarioRepository.save(new Domiciliario("Gabriela Pineda", "3006789000", "1234567816", true));
             domiciliarioRepository.save(new Domiciliario("Tomás Rojas", "3007890000", "1234567817", true));
             domiciliarioRepository.save(new Domiciliario("Lucía Cárdenas", "3008900000", "1234567818", true));
             domiciliarioRepository.save(new Domiciliario("Juan Esteban", "3009010000", "1234567819", true));
             domiciliarioRepository.save(new Domiciliario("Paula Mejía", "3000120000", "1234567820", true));
            
            
             operadorRepository.save(new Operador("María González", "maria.gonzalez", "maria123"));
             operadorRepository.save(new Operador("Carlos Ramírez", "carlos.ramirez", "carlos123"));
             operadorRepository.save(new Operador("Ana López", "ana.lopez", "ana123"));
             operadorRepository.save(new Operador("Luis Martínez", "luis.martinez", "luis123"));
             operadorRepository.save(new Operador("Sofía Torres", "sofia.torres", "sofia123"));
             operadorRepository.save(new Operador("Jorge Pérez", "jorge.perez", "jorge123"));
             operadorRepository.save(new Operador("Isabel Sánchez", "isabel.sanchez", "isabel123"));
             operadorRepository.save(new Operador("Diego Castro", "diego.castro", "diego123"));
             operadorRepository.save(new Operador("Valentina Morales", "valentina.morales", "valentina123"));
             operadorRepository.save(new Operador("Andrés Herrera", "andres.herrera", "andres123"));

             // Crear y guardar productos
            clienteRepository.save(new Cliente("Juan", "Bermudez", "joseber@hotmail.com", "123456", "1234567890", "Calle Falsa 123"));
            // Crear y guardar productos
            
            Producto producto2 = new Producto("Chichanorrada", 18.00f, "Crujientes trozos de cerdo fritos hasta alcanzar el punto perfecto de dorado y jugosidad.", "/images/entrada2.png");
            productoRepository.save(producto2);
            Producto producto3 = new Producto("Empanadas", 12.00f, "nuestras empanadas rellenas de carne, pollo o queso te ofrecen un sabor auténtico en cada bocado.", "/images/entrada3.png");
            productoRepository.save(producto3);
            Producto producto4 = new Producto("Chorizada", 18.00f, "Sabroso chorizo artesanal con el toque justo de especias.", "/images/entrada4.png");
            productoRepository.save(producto4);
            
            Producto producto6 = new Producto("Tomahawk", 62.00f, "Un impresionante corte de carne a la parrilla, con hueso largo y jugoso, ideal para los amantes de la carne roja.", "/images/pf1.png");
            productoRepository.save(producto6);
            Producto producto7 = new Producto("BabyBeef", 48.00f, "Corte tierno y jugoso de carne, cuidadosamente asado para ofrecer una textura suave y un sabor delicioso que se derrite en la boca.", "/images/pf2.png");
            productoRepository.save(producto7);
            Producto producto8 = new Producto("Churrasco", 48.00f, "Corte de res perfectamente sazonado y asado a la parrilla, que resalta su sabor natural con una jugosidad inigualable.", "/images/pf3.png");
            productoRepository.save(producto8);
            Producto producto9 = new Producto("CostillasBBQ", 58.00f, "Costillas de cerdo marinadas en una salsa barbacoa casera, cocinadas a fuego lento hasta quedar tiernas y con un sabor ahumado delicioso.", "/images/pf4.png");
            productoRepository.save(producto9);
            Producto producto10 = new Producto("LomoDeRes", 48.00f, "Un corte fino de res, tierno y jugoso, asado a la perfección para resaltar su sabor natural. Ideal para los que buscan calidad en cada bocado.", "/images/pf5.png");
            productoRepository.save(producto10);
            Producto producto11 = new Producto("Maracuyoso", 12.00f, "Una dulce y refrescante crema de maracuyá que combina el sabor ácido de la fruta con la suavidad de la crema, un final perfecto para tu comida.", "/images/postre1.png");
            productoRepository.save(producto11);
            Producto producto12 = new Producto("Limonoso", 12.00f, "Delicioso postre de limón, refrescante y con un toque ácido perfecto para equilibrar cualquier comida, ideal para los amantes de los sabores cítricos.", "/images/postre2.png");
            productoRepository.save(producto12);
            Producto producto13 = new Producto("Flan de Coco", 12.00f, "Suave flan con un toque de coco rallado, cremoso y con la dulzura justa, que hará que tu paladar disfrute cada cucharada.", "/images/postre3.png");
            productoRepository.save(producto13);
            Producto producto14 = new Producto("Miloso", 12.00f, "Un postre cremoso con el sabor único del Milo, un toque de chocolate con malta que te hará recordar los sabores de la infancia.", "/images/postre4.png");
            productoRepository.save(producto14);
            Producto producto15 = new Producto("Tiramisú Clásico", 15.00f, "Delicioso postre italiano con capas de bizcocho empapado en café, crema de mascarpone y cacao en polvo.", "/images/postre5.jpg");
            productoRepository.save(producto15);
            Producto producto16 = new Producto("Cheesecake de Frutos Rojos", 14.00f, "Suave y cremoso cheesecake con una cobertura de frutos rojos que le da un toque dulce y ácido irresistible.", "/images/postre6.jpg");
            productoRepository.save(producto16);
            Producto producto17 = new Producto("Brownie con Helado", 16.00f, "Brownie de chocolate caliente acompañado de una bola de helado de vainilla, una combinación perfecta de texturas y sabores.", "/images/postre7.jpg");
            productoRepository.save(producto17);
            Producto producto18 = new Producto("Coca cola", 5.00f, "La refrescante bebida clásica que no puede faltar, con su sabor único y burbujeante, perfecta para acompañar tu comida.", "/images/bebida1.png");
            productoRepository.save(producto18);
            Producto producto19 = new Producto("Coca cola zero", 5.00f, "La opción sin calorías, con el sabor refrescante y delicioso de la Coca-Cola original, ideal para quienes prefieren evitar el azúcar.", "/images/bebida2.png");
            productoRepository.save(producto19);
            Producto producto20 = new Producto("Sprite", 5.00f, "Bebida refrescante y cítrica, con el sabor burbujeante de limón-lima, ideal para disfrutar en cualquier momento.", "/images/bebida3.png");
            productoRepository.save(producto20);
            Producto producto21 = new Producto("Coctel de maracuya", 28.00f, "Refrescante cóctel con el toque tropical y ácido del maracuyá, combinado con ingredientes naturales que harán de tu bebida un verdadero placer.", "/images/bebida4.png");
            productoRepository.save(producto21);
            Producto producto22 = new Producto("Agua sin gas", 5.00f, "Agua fresca y purificada, la opción más natural y saludable para acompañar tu comida.", "/images/bebida5.png");
            productoRepository.save(producto22);

            Producto producto23 = new Producto("Coctel Frutos rojos", 28.00f, "Una mezcla vibrante de frutos rojos frescos, ideal para los que disfrutan de una bebida afrutada y ligeramente dulce.", "/images/bebida6.png");
            productoRepository.save(producto23);

            Producto producto24 = new Producto("Mojito Clásico", 25.00f, "Refrescante combinación de ron, hierbabuena, lima y soda, ideal para disfrutar en cualquier ocasión.", "/images/bebida7.jpg");
            productoRepository.save(producto24);

            Producto producto25 = new Producto("Piña Colada", 30.00f, "Delicioso cóctel tropical con ron, crema de coco y jugo de piña, perfecto para un día soleado.", "/images/bebida8.jpg");
            productoRepository.save(producto25);

            Producto producto26 = new Producto("Daiquiri de Fresa", 27.00f, "Una mezcla dulce y refrescante de ron, fresas naturales y un toque de jugo de limón.", "/images/bebida9.jpg");
            productoRepository.save(producto26);

            Producto producto27 = new Producto("Caipirinha", 26.00f, "El clásico brasileño con cachaça, azúcar y lima, ideal para los amantes de los sabores cítricos.", "/images/bebida10.jpg");
            productoRepository.save(producto27);

            Producto producto28 = new Producto("Tequila Sunrise", 29.00f, "Vibrante combinación de tequila, jugo de naranja y granadina que simula un hermoso amanecer.", "/images/bebida11.jpg");
            productoRepository.save(producto28);

            Producto producto29 = new Producto("Blue Lagoon", 28.00f, "Exótico cóctel azul con vodka, curaçao azul y limón, que te transportará a un paraíso tropical.", "/images/bebida12.jpg");
            productoRepository.save(producto29);

            Producto producto30 = new Producto("Sex on the Beach", 31.00f, "Dulce y afrutado cóctel con vodka, licor de durazno, jugo de arándano y naranja.", "/images/bebida13.jpg");
            productoRepository.save(producto30);

            Producto producto31 = new Producto("Manhattan", 35.00f, "Elegante y sofisticada mezcla de whisky, vermut rojo y angostura, un clásico de la coctelería.", "/images/bebida7.png");
            productoRepository.save(producto31);

            Producto producto32 = new Producto("Cosmopolitan", 32.00f, "Famoso cóctel con vodka, licor de naranja, jugo de arándano y limón, ideal para una noche especial.", "/images/bebida10.png");
            productoRepository.save(producto32);

            Producto producto33 = new Producto("Mai Tai", 33.00f, "Exótica combinación de ron oscuro, ron blanco, triple sec y jugo de lima, un clásico tiki imprescindible.", "/images/bebida8.png");
            productoRepository.save(producto33);













            // Crear y guardar adicionales
            Adicional adicional1 = new Adicional("Papa Casco", 8.00f, "Papas al estilo tradicional, servidas en su cáscara, fritas hasta quedar doradas y crujientes, una opción sabrosa para acompañar tu plato.");
            adicionalRepository.save(adicional1);
            Adicional adicional2 = new Adicional("Papas francesas", 8.00f, "Papas fritas en corte fino, crujientes por fuera y suaves por dentro, perfectas para acompañar cualquier plato o disfrutar solas.");
            adicionalRepository.save(adicional2);
            Adicional adicional3 = new Adicional("Yuquitas", 8.00f, "Papas fritas en corte fino, crujientes por fuera y suaves por dentro, perfectas para acompañar cualquier plato o disfrutar solas.");
            adicionalRepository.save(adicional3);
            Adicional adicional4 = new Adicional("Papa Horneada", 12.00f, "Papas al horno, suaves por dentro y ligeramente crujientes por fuera, perfectas para acompañar cualquiera de nuestras carnes.");
            adicionalRepository.save(adicional4);
            Adicional adicional5 = new Adicional("Chimichurri", 12.00f, "Salsa fresca a base de ajo, perejil, vinagre y aceite, el acompañamiento perfecto para realzar el sabor de tu carne a la parrilla.");
            adicionalRepository.save(adicional5);
            Adicional adicional6 = new Adicional("Arroz", 12.00f, "Arroz esponjoso, ligeramente sazonado, un acompañante clásico que nunca puede faltar en nuestra mesa.");
            adicionalRepository.save(adicional6);
            Adicional adicional7 = new Adicional("Guacamole", 8.00f, "Crema suave y fresca de aguacate con el toque perfecto de limón, cebolla y cilantro. Ideal para acompañar nuestros platos y disfrutar con totopos.");
            adicionalRepository.save(adicional7);
            Adicional adicional8 = new Adicional("Arepitas", 10.00f, "Deliciosas arepas pequeñas, doradas y crujientes, ideales para acompañar carnes o degustar con queso.");
            adicionalRepository.save(adicional8);
            Adicional adicional9 = new Adicional("Tostones", 9.00f, "Plátanos verdes fritos en rodajas gruesas, crujientes y perfectos para acompañar cualquier plato.");
            adicionalRepository.save(adicional9);
            Adicional adicional10 = new Adicional("Ensalada fresca", 10.00f, "Mezcla de lechugas, tomate, zanahoria y pepino con aderezo ligero, perfecta para complementar tu comida.");
            adicionalRepository.save(adicional10);
            Adicional adicional11 = new Adicional("Salsa de ajo", 7.00f, "Salsa cremosa a base de ajo, perfecta para acompañar carnes, papas y otros platillos.");
            adicionalRepository.save(adicional11);
            Adicional adicional12 = new Adicional("Pan de ajo", 10.00f, "Rebanadas de pan tostado con mantequilla de ajo, ideales para acompañar pastas y carnes.");
            adicionalRepository.save(adicional12);
            Adicional adicional13 = new Adicional("Frijoles charros", 12.00f, "Frijoles cocidos con tocino, chorizo y especias, un acompañamiento lleno de sabor.");
            adicionalRepository.save(adicional13);
            Adicional adicional14 = new Adicional("Queso fundido", 15.00f, "Queso derretido con un toque de especias, ideal para acompañar nachos o tortillas.");
            adicionalRepository.save(adicional14);
            Adicional adicional15 = new Adicional("Maíz tierno", 8.00f, "Granos de maíz dulce, cocidos y sazonados, un acompañamiento ligero y delicioso.");
            adicionalRepository.save(adicional15);
            Adicional adicional16 = new Adicional("Puré de papa", 10.00f, "Puré cremoso de papas con mantequilla y un toque de sal, ideal para acompañar cualquier plato.");
            adicionalRepository.save(adicional16);
            Adicional adicional17 = new Adicional("Cebolla caramelizada", 8.00f, "Cebolla cocida lentamente hasta obtener un sabor dulce y suave, perfecta para carnes y hamburguesas.");
            adicionalRepository.save(adicional17);
            Adicional adicional18 = new Adicional("Pimentones asados", 9.00f, "Pimientos asados al carbón, con un sabor ahumado que realza cualquier plato.");
            adicionalRepository.save(adicional18);
            Adicional adicional19 = new Adicional("Champiñones salteados", 12.00f, "Champiñones frescos salteados en mantequilla y ajo, un acompañamiento delicioso para carnes y pastas.");
            adicionalRepository.save(adicional19);
            Adicional adicional20 = new Adicional("Plátano maduro", 8.00f, "Rodajas de plátano maduro caramelizado, un acompañamiento dulce y perfecto para contrastar sabores.");
            adicionalRepository.save(adicional20);


        // Establecer relaciones muchos a muchos
            producto6.addAdicional(adicional1);
            producto6.addAdicional(adicional2);
            producto6.addAdicional(adicional3);
            producto7.addAdicional(adicional4);
            producto7.addAdicional(adicional5);
            producto7.addAdicional(adicional6);
            producto8.addAdicional(adicional7);
            producto8.addAdicional(adicional8);
            producto8.addAdicional(adicional9);
            producto9.addAdicional(adicional10);
            producto9.addAdicional(adicional11);
            producto9.addAdicional(adicional12);
            producto10.addAdicional(adicional13);
            producto10.addAdicional(adicional14);
            producto10.addAdicional(adicional15);
            

            // Guardar el producto con los adicionales
            productoRepository.save(producto6);
            productoRepository.save(producto7);
            productoRepository.save(producto8);
            productoRepository.save(producto9);
            productoRepository.save(producto10);


            
           
        
        }
    }

