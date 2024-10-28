<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/documentation/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'tintuc' );

/** Database username */
define( 'DB_USER', 'root' );

/** Database password */
define( 'DB_PASSWORD', '' );

/** Database hostname */
define( 'DB_HOST', 'localhost' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'Yr3j0G3]4;v:9`&R!Tfx[{kZOa39+iYZJy/YHfw*XWD}pYvfjR!vdkcORiE]3@(M' );
define( 'SECURE_AUTH_KEY',  '*%jmj)QY`7F}YWF!ca[hS$4&e3K+J[ =LLEw$3OR]5/_{t2<@VQ1C~2l-CjuRImO' );
define( 'LOGGED_IN_KEY',    '*>6@k7HfNL@R.XjXHmMwc09tU(oAw[w:1p8|FpLx?>xdAz(xQx1F*E`=+pcRs#)4' );
define( 'NONCE_KEY',        'mfED-$6+^KQ~uh0ZE2Mpl@dcSQJCLVYggIY4&nP+2$DJ/rT_G7<Xzo#9><@O5nNN' );
define( 'AUTH_SALT',        'vamgAQSf;BfO=an=Piit0d0+s6;H%v-x8p3vnteV,/ad%u`=m`Ts{nS,K#)3lLlG' );
define( 'SECURE_AUTH_SALT', 'to[qjBoSCwT)u6Z@<cUtRG{$ip C+RAgK-* X(I9,.2ZThUn:eq3]n,:y;J<1GX;' );
define( 'LOGGED_IN_SALT',   '}!r3%EdMofH}s;bB;rIHz`@8vR2$hD-RvID9%KqO]lM`tcK:c7!l?o-J&Ond3Yvu' );
define( 'NONCE_SALT',       ';Vn}*&mxI:WTuqQ+ )+=Z:$^V.arI|0oD6CT=y/o@y0T(CZ[`-z8|oKnj>4@oStv' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/documentation/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
