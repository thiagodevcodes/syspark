import styles from "./footer.module.css"
import { ReactElement } from "react" 
import Link from "next/link"


export default function Footer(): ReactElement {

    return (
        <footer className={styles.footer}>
            <div className={styles.container}>
                <p>Copyright 2024 &copy; Developed by <Link href={"https://thiagodev.vercel.app"}>Thiago Silva Rodrigues</Link>  & <Link href={"https://alexandreloiola.galatus.com.br/"}>Alexandre Loiola</Link></p>
            </div>
        </footer>
    )
}