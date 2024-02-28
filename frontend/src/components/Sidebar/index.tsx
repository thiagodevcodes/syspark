import styles from "./sidebar.module.css"
import { ReactElement } from "react" 
import Link from "next/link"
import Image from "next/image"

interface MobileButtonProps {
    setActive?: React.Dispatch<React.SetStateAction<boolean>>;
    active?: boolean;
}

export default function Aside({ setActive, active}: MobileButtonProps): ReactElement {
    return (
        <aside className={`${styles.aside} ${active ? `${styles.open}` : ""}`} >
            <ul>
                <li>
                    <Image src={"/icons/Home.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"/home"}>Home</Link>
                </li>
                <li>
                    <Image src={"/icons/Calendar.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"#"}>Mensalistas</Link>
                </li>
                <li>
                    <Image src={"/icons/Movement.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"#"}>Movimentações</Link>
                </li>
                <li>
                    <Image src={"/icons/Money.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"#"}>Preços</Link>
                </li>
                <li>
                    <Image src={"/icons/Users.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"#"}>Usuários</Link>
                </li>
                <li>
                    <Image src={"/icons/Relatorio.svg"} width={25} height={25} alt="Logo Home"></Image>
                    <Link href={"#"}>Relatórios</Link>
                </li>
            </ul>
        </aside>
    )
}